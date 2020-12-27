package com.nucleus.chargepolicy.controller;



import com.nucleus.charge.model.NewCharge;
import com.nucleus.chargepolicy.service.ChargePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * This class acts as a Controller for all
 * Charge Policy related operations.
 *
 */
@Controller
@PropertySource("classpath:status.properties")
@RequestMapping("chargePolicy")
public class ChargePolicyController {

    @Autowired
    ChargePolicyService chargePolicyService;

    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.saved}"))
    private String saved;

    private static final String CHARGE_CODE_LIST = "chargeCodeList";
    private static final String CAHRGE_CODE_NAME = "chargeCodeName";
    private static final String CHARGE_POLICY_EDIT_PAGE = "views/chargepolicy/chargePolicy";
    private static final String STATUS = "status";
    private static final String CHARGE_POLICY_SUCCESS_PAGE = "views/chargepolicy/SuccessPage";

    public ChargePolicyService getChargePolicyService() {
        return chargePolicyService;
    }

    public void setChargePolicyService(ChargePolicyService chargePolicyService) {
        this.chargePolicyService = chargePolicyService;
    }

    /**
     *  This method is used to populate the charge code list which will be displayed on charge policy edit page.
     * @param model
     * @return It returns a view with charge code list and corresponding charge code name
     */
    @GetMapping(value = {"/newChargePolicy"})
    public String showForm(ModelMap model) {
        ChargePolicy chargePolicy = new ChargePolicy();

        List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
        if(chargeCodeList.isEmpty()){
            model.put("error", "No Approved Charges in the database, Please approve the charges and try again");
            return "views/chargepolicy/errorPage";
        }
        model.put("chargePolicy", chargePolicy);
        model.put(CHARGE_CODE_LIST, chargeCodeList);
        model.put(CAHRGE_CODE_NAME,getChargeCodeName(chargeCodeList.get(0)));
        return CHARGE_POLICY_EDIT_PAGE;
    }


    /**
     * This method is called when the form is submitted.
     * @param chargePolicy This is the model attribute received through the form
     * @param bindingResult
     * @param action This action corresponds to the button(Save or Save & Request Approval) clicked by the user in the view
     * @param model The model object is used to send success and error messages to the next page.
     * @return String which corresponds to the view which depends on the status of insertion.
     */
    @PostMapping(value = {"/newChargePolicy"})
    public String getFormData(@Valid @ModelAttribute("chargePolicy") ChargePolicy chargePolicy, BindingResult bindingResult, @RequestParam("action") String action, ModelMap model) {

        if (bindingResult.hasErrors()) {
            List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
            model.put(CHARGE_CODE_LIST, chargeCodeList);
            return CHARGE_POLICY_EDIT_PAGE;
        } else {
            if (action.equalsIgnoreCase("save")) {
                chargePolicy.setStatus(saved);
            } else {
                chargePolicy.setStatus(pending);
            }
            chargePolicy.setCreatedDate(LocalDate.now());
            chargePolicy.setCreatedBy(getPrincipal());
            int status = this.chargePolicyService.insert(chargePolicy);
            if (status == 3) {
                model.put("exception", "Duplicate Charge Policy Code");
                List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
                model.put(CHARGE_CODE_LIST, chargeCodeList);
                model.put(CAHRGE_CODE_NAME,getChargeCodeName(chargeCodeList.get(0)));
                return CHARGE_POLICY_EDIT_PAGE;
            } else if (status == 1) {
                model.put("chargePolicyCode", chargePolicy.getChargePolicyCode());
                model.put(STATUS, "saved");
                return CHARGE_POLICY_SUCCESS_PAGE;
            }
            else if (status == 2 ) {
                model.put("error", "Some error occured, Please check logs. ");
                return "views/chargepolicy/errorPage";
            }

        }
        return CHARGE_POLICY_EDIT_PAGE;
    }

    /**
     * This method is called before the search screen and is used to populate the table with a list of charge policies.
     * @param modelMap It is sent to the search screen with a list of charge policies
     * @return view corresponding to the search screen
     */
    @GetMapping(value = {"/searchScreen"})
    public String showAllEligibilityPolicies(ModelMap modelMap) {
        List<ChargePolicy> chargePolicyList = new ArrayList<>();
        chargePolicyList.addAll(this.chargePolicyService.getPolicyList());
        ChargePolicy chargePolicy = new ChargePolicy();
        modelMap.put("chargePolicy", chargePolicy);
        modelMap.put("chargePolicyList", chargePolicyList);
        return "views/chargepolicy/chargePolicySearch";
    }


    /**
     * This method is used to populate the charge code name field in the view on the basis of selected charge code.
     * @param charge It is the charge which is currently in the select field.
     * @return NewCharge object through which information like charge code and charge code name is taken.
     */
    @PostMapping(value = {"/newChargePolicy/getCharge", "edit/newChargePolicy/getCharge"}, produces = "application/json")
    public @ResponseBody NewCharge getCharge(@RequestBody NewCharge charge) {
        charge.setChargeCodeName(getChargeCodeName(charge.getChargeCode()));
        return charge;

    }

    /**
     * This method is used to show all details of one charge policy on the screen.
     * @param chargePolicyCode This holds the primary key on the basis of which search for charge policy has
     *                         to be carried out.
     * @return ModelandView containing information about the charge policy which is to be reviewed.
     */
    @GetMapping(value = {"/get/{chargePolicyCode}"})
    public ModelAndView showOneChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {
        ModelAndView modelAndView = new ModelAndView();
        ChargePolicy chargePolicy = chargePolicyService.getChargePolicy(chargePolicyCode);
        modelAndView.addObject("chargePolicyForApproval", chargePolicy);
        modelAndView.addObject(CAHRGE_CODE_NAME, getChargeCodeName(chargePolicy.getCharge().getChargeCode()));
        modelAndView.setViewName("views/chargepolicy/chargePolicyApprovalScreen");
        return modelAndView;
    }

    /**
     * This method is used to display all the details of one charge policy.
     * @param chargePolicyCode This holds the primary key on the basis of which search for charge policy has
     *                         to be carried out.
     * @return ModelandView containing information of one charge policy corresponding to the policy_code
     */
    @GetMapping(value = {"/edit/{chargePolicyCode}"})
    public ModelAndView editChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {

        ModelAndView modelAndView = new ModelAndView();
        ChargePolicy chargePolicy = chargePolicyService.getChargePolicy(chargePolicyCode);
        List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
        modelAndView.addObject(CHARGE_CODE_LIST, chargeCodeList);
        modelAndView.addObject("chargePolicyForEdit", chargePolicy);
        modelAndView.addObject("chargePolicySelected", chargePolicy.getCharge().getChargeCode());
        String chargeCodeName = this.chargePolicyService.getChargeCodeName(chargePolicy.getCharge().getChargeCode());
        modelAndView.addObject(CAHRGE_CODE_NAME, chargeCodeName);
        modelAndView.setViewName("views/chargepolicy/chargePolicyEditScreen");
        return modelAndView;
    }

    /**
     * This method is used to delete a single charge policy from the database
     * @param chargePolicyCode policy code of the charge policy to be deleted
     * @return modelAndView containing information of the charge policy which had to be deleted
     *         and status.
     */
    @GetMapping(value = {"/delete/{chargePolicyCode}"})
    public ModelAndView deleteChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {
        ModelAndView modelAndView = new ModelAndView();
        chargePolicyService.deleteChargePolicy(chargePolicyCode);
        modelAndView.addObject("chargePolicyCode", chargePolicyCode);
        modelAndView.addObject(STATUS, "deleted");
        modelAndView.setViewName(CHARGE_POLICY_SUCCESS_PAGE);
        return modelAndView;
    }

    /**
     * This method is used to mark a particular charge policy as 'Approved' or 'Rejected'
     * @param chargePolicyCode policy code of the charge policy
     * @param action whether it has been approved or not
     * @param model
     * @return redirects to the search screen with modified status field.
     */
    @PostMapping(value = {"/updateStatus/{chargePolicyCode}"})
    public String updateStatus(@PathVariable("chargePolicyCode") String chargePolicyCode, @RequestParam("action") String action, Model model) {
        String newStatus;
        if (action.equalsIgnoreCase("approve")) {
            newStatus = approved;
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = rejected;
        } else
            newStatus = pending;
        String approvedBy = getPrincipal();
        this.chargePolicyService.updateStatus(chargePolicyCode, newStatus, approvedBy);
        return "redirect:/chargePolicy/searchScreen";
    }

    /**
     * This method is used to update all the detail of a single charge policy
     * @param action this consists of two values 'Saved' and 'Pending' which is in accordance with the button clicked
     *               by the user
     * @param chargePolicyCode policy code of the charge policy to be modified
     * @param chargePolicy charge policy to be modified
     * @return modelAndView which contains the information of the modified charge policy.
     */
    @PostMapping(value = {"/updateEntry/{chargePolicyCode}"})
    public ModelAndView updateEntry(@RequestParam("action")String action,@PathVariable("chargePolicyCode") String chargePolicyCode, @ModelAttribute ChargePolicy chargePolicy) {
        ModelAndView modelAndView = new ModelAndView();
        chargePolicy.setModifiedDate(LocalDate.now());
        //chargePolicy.setStatus(this.chargePolicyService.getChargePolicy(chargePolicyCode).getStatus());
        if (action.equalsIgnoreCase("save")) {
            chargePolicy.setStatus(saved);
        } else {
            chargePolicy.setStatus(pending);
        }
        chargePolicy.setModifiedBy(getPrincipal());
        boolean status = this.chargePolicyService.updateEntry(chargePolicy, chargePolicyCode);
        if(status){
            modelAndView.addObject(STATUS, "edited");
            modelAndView.setViewName(CHARGE_POLICY_SUCCESS_PAGE);
        }
        else{
            modelAndView.addObject("error", "Charge Policy Not Updated.");
            modelAndView.addObject("message", "Charge Policy name cannot contain spaces or special characters!");
            modelAndView.setViewName("views/chargepolicy/errorPage");
        }

        modelAndView.addObject("chargePolicyCode", chargePolicyCode);

        return modelAndView;
    }

    /**
     * This method gets the user who is currently on the session
     * @return
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private String getChargeCodeName(String chargeCode) {
        return this.chargePolicyService.getChargeCodeName(chargeCode);
    }
}
