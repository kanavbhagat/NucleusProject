package com.nucleus.charge.controllers;

import com.nucleus.charge.model.NewCharge;
import com.nucleus.charge.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a Controller for all
 * Charge related operations.
 *
 */

@Controller
@PropertySource("classpath:status.properties")
@RequestMapping("/charges")
public class ChargeDisplayController {

    @Autowired
    private ChargeService chargeService;

    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.saved}"))
    private String saved;
    
    private static final String ERROR = "views/charge/moduleerrorpage";
    private static final String SUCCESS = "views/charge/success";

    /**
     * This method is used to display a list of existing Charges to maker.
     *
     * @param model This model object is used to display charge list.
     *
     * @return String This returns path to specified view with a list of all Charges.
     */
    @GetMapping("/makerList")
    @PreAuthorize("hasRole('MAKER')")
    public String chargeDisplay(ModelMap model) {
        List<NewCharge> chargeList = chargeService.getChargeList();
        model.put("chargeList",chargeList);
        return "views/charge/chargeScreen";
    }


    /**
     * This method is used to display an approved or rejected Charge.
     *
     * @param chargeCode This holds the code of charge whose fields need to be displayed.
     * @param model This model object is used to display charge fields.
     *
     * @return String This returns path to a view according to the data received.
     */
    @GetMapping("/displayApplication/{chargeCode}")
    @PreAuthorize("hasRole('MAKER')")
    public String displayApprovedCharge(@PathVariable("chargeCode") String chargeCode,
                                        ModelMap model) {
        NewCharge charge = chargeService.getOneCharge(chargeCode);
        model.put("newChargeData", charge);
        return "views/charge/approvedChargeDisplay";
    }

    /**
     * This method is used to display a list of existing Charges which are waiting for approval for checker.
     *
     * @param model This model object is used to display form.
     * @return String This returns path to a specified view with all Pending Charges.
     */
    @GetMapping("/checkerList")
    @PreAuthorize("hasRole('CHECKER')")
    public String pendingChargeDisplay(ModelMap model) {
        List<NewCharge> chargeList = chargeService.getPendingChargeList();
        model.put("chargeList",chargeList);
        return "views/charge/chargeScreen";

    }

    /**
     * This method is used to display the form for adding a new Charge
     *
     * @param model This model object is used to display form
     *
     * @return String This returns path to a specified view .
     */
    @GetMapping("/newChargeCreation")
    @PreAuthorize("hasRole('MAKER')")
    public String showForm(ModelMap model) {
        NewCharge charge = new NewCharge();
        model.put("newChargeData", charge);
        return ("views/charge/chargeDefinition");
    }

    /**
     * This method is used to add a Charge (entered by the maker) to the database.
     *
     * @param action This holds one of two actions,
     *               (i.e. Save/Save & Request Approval), that decide the status.
     *
     * @param charge This is the modelAttribute received from the form.
     * @param result This contains data validation errors, if any.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PostMapping("/newChargeCreation")
    @PreAuthorize("hasRole('MAKER')")
    public String onSubmit(@RequestParam("action") String action,
                           @Valid @ModelAttribute("newChargeData") NewCharge charge,
                           BindingResult result,
                           Model model) {
        if(result.hasErrors()) {
            return "views/charge/chargeDefinition";
        }
        else {
            String status = null;
            if(action.equalsIgnoreCase("save")) {
                status = saved;
            }
            else if(action.equalsIgnoreCase("save & request approval")) {
                status = pending;
            }
            int insertStatus = chargeService.insertCharge(charge, status);
            if(insertStatus == 1) {
                model.addAttribute("head","Charge Created Successfully");
                model.addAttribute("msg","Your Charge has been created successfully.");
                model.addAttribute("chargeCode",charge.getChargeCode());
                return SUCCESS;
            }
            else if(insertStatus == 2) {
                model.addAttribute("description", "Failed to create charge.");
                model.addAttribute("chargeCode", "Duplicate Charge Code or Charge Code Name.");
                return "views/charge/createErrorPage";
            }
            else {
                model.addAttribute("description","Failed to create charge.");
                model.addAttribute("chargeCode",charge.getChargeCode());
                return ERROR;
            }
        }
    }


    /**
     * This method is used to display details of one Charge
     * based on checker's selection of hyperlink of code.
     *
     * @param chargeCode This holds charge code of the Charge
     *                   that has to be displayed.
     * @param model This model object is used to view form with read-only details.
     *
     * @return String This returns a path to specified view.
     */
    @GetMapping("{chargeCode}")
    @PreAuthorize("hasRole('CHECKER')")
    public String getSpecifiedCharge(@PathVariable("chargeCode") String chargeCode,
                                     ModelMap model)
    {
        NewCharge charge = chargeService.getOneCharge(chargeCode);
        model.put("newChargeData", charge);
        return "views/charge/approvedChargeDisplay";
    }

    /**
     * This method is used to add a Charge (entered by the maker) to the database.
     *
     * @param action This holds one of two actions,
     *               (i.e. Approve/Reject), that decide the status.
     *
     * @param chargeCode This is the code of the charge that needs to be approved or rejected.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PostMapping("{chargeCode}")
    @PreAuthorize("hasRole('CHECKER')")
    public String chargeChecker(@PathVariable("chargeCode") String chargeCode,
                                @RequestParam("action") String action,
                                Model model) {
        String status = null;
        if(action.equalsIgnoreCase("approve")) {
            status = approved;
        }else if(action.equalsIgnoreCase("reject")) {
            status = rejected;
        }
        boolean b = chargeService.updateStatus(chargeCode, status);
        if(b) {
            if(action.equalsIgnoreCase( "approve")) {
                model.addAttribute("head","Charge Approved Successfully");
                model.addAttribute("msg","Charge has been approved successfully.");
            }
            else if(action.equalsIgnoreCase("reject")) {
                model.addAttribute("head","Charge Rejected Successfully");
                model.addAttribute("msg","Your Charge has been rejected successfully.");
            }
            model.addAttribute("chargeCode", chargeCode);
            return SUCCESS;
        }
        else {
            if (action.equalsIgnoreCase("approve")) {
                model.addAttribute("description", "Failed to approve charge.");
            } else if (action.equalsIgnoreCase("reject")) {
                model.addAttribute("description", "Failed to reject charge.");
            }
            model.addAttribute("chargeCode", chargeCode);
            return ERROR;
        }
    }

    /**
     * This method is used to delete an existing Charge from database.
     *
     * @param chargeCode This holds charge code of the Charge
     *                   which is going to be deleted.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view with acknowledgement.
     */
    @RequestMapping(value = {"/delete/{chargeCode}"})
    @PreAuthorize("hasRole('MAKER')")
    public String deleteCharge(@PathVariable("chargeCode") String chargeCode, Model model) {
        boolean deleteStatus = chargeService.deleteCharge(chargeCode);
        if(deleteStatus) {
            model.addAttribute("head","Charge Deleted Successfully");
            model.addAttribute("msg","Your Charge has been deleted successfully.");
            model.addAttribute("chargeCode",chargeCode);
            return SUCCESS;
        }
        else {
            model.addAttribute("description","Failed to delete charge.");
            model.addAttribute("chargeCode",chargeCode);
            return ERROR;
        }
    }

    /**
     * This method is used to display editable details of existing Charge.
     *
     * @param chargeCode This holds charge code of the Charge
     *                   which is going to be edited.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PreAuthorize("hasRole('MAKER')")
    @RequestMapping(value = {"/edit/{chargeCode}"})
    public String getEditPolicyPage(@PathVariable("chargeCode") String chargeCode, Model model) {
        NewCharge charge = chargeService.getOneCharge(chargeCode);
        model.addAttribute("newCharge", charge);
        return "views/charge/editOneCharge";
    }

    /**
     * This method is used to update Eligibility Policy after user has edited the fields.
     *
     * @param action This holds one of two actions,
     *               (i.e. Save/Save & Request Approval), that decide the status.
     * @param charge This is the modelAttribute received from the form after editing.
     * @param result This contains data validation errors, if any.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a acknowledgement view.
     */
    @PreAuthorize("hasRole('MAKER')")
    @PostMapping(value = {"edit/addEdited"})
    public String addEditedCharge(@RequestParam("action")String action,
                                  @Valid @ModelAttribute("newCharge") NewCharge charge,
                                  BindingResult result,
                                  Model model) {
        if(result.hasErrors()) {
            return "views/charge/editOneCharge";
        }
        else {
            if (action.equalsIgnoreCase("save")) {
                charge.setStatus(saved);
            } else if (action.equalsIgnoreCase("save & request approval")) {
                charge.setStatus(pending);
            }

            boolean editStatus = chargeService.updateCharge(charge);
            if (editStatus) {
                model.addAttribute("head", "Charge Edited Successfully");
                model.addAttribute("msg", "Your Charge has been edited successfully.");
                model.addAttribute("chargeCode", charge.getChargeCode());
                return SUCCESS;
            } else {
                model.addAttribute("description", "Failed to edit charge.");
                model.addAttribute("chargeCode", charge.getChargeCode());
                return ERROR;
            }
        }
    }

    /**
     * This method acts as model attribute to get dynamic options for Transaction Event.
     * @return List This returns list of options.
     */
    @ModelAttribute("eventList")
    public List<String> populateEventList() {
        List<String> eventList = new ArrayList<>();
        eventList.add("Processing Fees");
        eventList.add("Repayment Reschedule Fees");
        eventList.add("Part Prepayment Fees");
        return eventList;
    }

    /**
     * This method acts as model attribute to get dynamic options for Charge Type.
     * @return List This returns list of options.
     */
    @ModelAttribute("chargeTypeList")
    public List<String> populateChargeTypeList() {
        List<String> chargeTypeList = new ArrayList<>();
        chargeTypeList.add("Amount Based");
        return chargeTypeList;
    }

    /**
     * This method acts as model attribute to get dynamic radio buttons for Charge Payment Type.
     * @return List This returns list of radio buttons.
     */
    @ModelAttribute("paymentTypeList")
    public List<String> populatePaymentType() {
        List<String> paymentTypeList = new ArrayList<>();
        paymentTypeList.add("Receivable");
        paymentTypeList.add("Payable");
        paymentTypeList.add("Both");
        return paymentTypeList;
    }

}
