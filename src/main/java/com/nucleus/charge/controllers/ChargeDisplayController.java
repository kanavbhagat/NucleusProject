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

@Controller
@PropertySource("classpath:status.properties")
@RequestMapping("/charges")
public class ChargeDisplayController {

    @Autowired
    ChargeService chargeService;

    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.saved}"))
    private String saved;

    @GetMapping("/makerList")
    @PreAuthorize("hasRole('MAKER')")
    public String chargeDisplay(ModelMap model) {
        List<NewCharge> chargeList = chargeService.getChargeList();
        model.put("chargeList",chargeList);
        return "views/charge/chargeScreen";
    }

    @GetMapping("/checkerList")
    @PreAuthorize("hasRole('CHECKER')")
    public String pendingChargeDisplay(ModelMap model) {
        List<NewCharge> chargeList = chargeService.getPendingChargeList();
        model.put("chargeList",chargeList);
        return "views/charge/chargeScreen";

    }

    @GetMapping("/newChargeCreation")
    @PreAuthorize("hasRole('MAKER')")
    public String showForm(ModelMap model) {
        NewCharge charge = new NewCharge();
        model.put("newChargeData", charge);
        return ("views/charge/chargeDefinition");
    }

    @PostMapping("/newChargeCreation")
    @PreAuthorize("hasRole('MAKER')")
    public String onSubmit(@RequestParam("action") String action,
                           @Valid @ModelAttribute("newChargeData") NewCharge charge,
                           BindingResult result,
                           Model model) {
        System.out.println("In getForm");
        if(result.hasErrors()) {
            System.out.println("Error occured while filling charge creation form...");
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
            boolean b = chargeService.insertCharge(charge, status);
            if(b) {
                model.addAttribute("head","Charge Created Successfully");
                model.addAttribute("msg","Your Charge has been created successfully.");
                model.addAttribute("chargeCode",charge.getChargeCode());
                return "views/charge/success";
            }
            else {
                model.addAttribute("description","Failed to create charge.");
                model.addAttribute("chargeCode",charge.getChargeCode());
                return "views/charge/moduleerrorpage";
            }
        }
    }


    @GetMapping("{chargeCode}")
    @PreAuthorize("hasRole('CHECKER')")
    public String getSpecifiedCharge(@PathVariable("chargeCode") String chargeCode,
                                     ModelMap model)
    {
        NewCharge charge = chargeService.getOneCharge(chargeCode);
        model.put("newChargeData", charge);
        return "views/charge/chargeDefinition";
    }

    @PostMapping("{chargeCode}")
    @PreAuthorize("hasRole('CHECKER')")
    public String chargeChecker(@PathVariable("chargeCode") String chargeCode,
                                @RequestParam("action") String action,
                                Model model) {
        System.out.println("In Charge Checker controller");
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
            return "views/charge/success";
        }
        else {
            if(action.equalsIgnoreCase( "approve")) {
                model.addAttribute("description","Failed to approve charge.");
            }
            else if(action.equalsIgnoreCase("reject")) {
                model.addAttribute("description","Failed to reject charge.");
            }
            model.addAttribute("chargeCode",chargeCode);
            return "views/charge/moduleerrorpage";
        }
        //return "redirect:/charges/checkerList";
    }

    @RequestMapping(value = {"/delete/{chargeCode}"})
    @PreAuthorize("hasRole('MAKER')")
    public String deleteCharge(@PathVariable("chargeCode") String chargeCode, Model model) {
        System.out.println("In charge Deletion controller");
        boolean deleteStatus = chargeService.deleteCharge(chargeCode);
        if(deleteStatus) {
            model.addAttribute("head","Charge Deleted Successfully");
            model.addAttribute("msg","Your Charge has been deleted successfully.");
            model.addAttribute("chargeCode",chargeCode);
            return "views/charge/success";
        }
        else {
            model.addAttribute("description","Failed to delete charge.");
            model.addAttribute("chargeCode",chargeCode);
            return "views/charge/moduleerrorpage";
        }
        //return "redirect:/charges/makerList";
    }

    @PreAuthorize("hasRole('MAKER')")
    @RequestMapping(value = {"/edit/{chargeCode}"})
    public String getEditPolicyPage(@PathVariable("chargeCode") String chargeCode, Model model) {
        System.out.println("In edit controller");
        NewCharge charge = chargeService.getOneCharge(chargeCode);
        model.addAttribute("charge", charge);
        return "views/charge/editOneCharge";
    }

    @PreAuthorize("hasRole('MAKER')")
    @PostMapping(value = {"edit/addEdited"})
    public String addEditedCharge(@RequestParam("action")String action,
                                  @ModelAttribute("charge") NewCharge charge,
                                  Model model) {

        System.out.println("In Addedit controller");
        if(action.equalsIgnoreCase("save")) {
            charge.setStatus(saved);
        } else if (action.equalsIgnoreCase("save & request approval")) {
            charge.setStatus(pending);
        }

        boolean editStatus = chargeService.updateCharge(charge);
        if(editStatus) {
            model.addAttribute("head","Charge Edited Successfully");
            model.addAttribute("msg","Your Charge has been edited successfully.");
            model.addAttribute("chargeCode",charge.getChargeCode());
            return "views/charge/success";
        }
        else {
            model.addAttribute("description","Failed to edit charge.");
            model.addAttribute("chargeCode",charge.getChargeCode());
            return "views/charge/moduleerrorpage";
        }
        //return "redirect:/charges/makerList";
    }

    @ModelAttribute("eventList")
    public List<String> populateEventList() {
        List<String> eventList = new ArrayList<>();
        eventList.add("Processing Fees");
        eventList.add("Repayment Reschedule Fees");
        eventList.add("Part Prepayment Fees");
        return eventList;
    }

    @ModelAttribute("chargeTypeList")
    public List<String> populateChargeTypeList() {
        List<String> chargeTypeList = new ArrayList<>();
        chargeTypeList.add("Amount Based");
        return chargeTypeList;
    }

    @ModelAttribute("paymentTypeList")
    public List<String> populatePaymentType() {
        List<String> paymentTypeList = new ArrayList<>();
        paymentTypeList.add("Receivable");
        paymentTypeList.add("Payable");
        paymentTypeList.add("Both");
        return paymentTypeList;
    }

}
