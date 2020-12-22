package com.nucleus.charge.controllers;

import com.nucleus.charge.model.NewCharge;
import com.nucleus.charge.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/charges")
public class ChargeDisplayController {

    @Autowired
    ChargeService chargeService;

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
                           BindingResult result) {
        if(result.hasErrors()) {
            return "views/charge/chargeDefinition";
        }
        else {
            String status = null;
            if(action.equalsIgnoreCase("save")) {
                status = "Inactive";
            }
            else if(action.equalsIgnoreCase("save & request approval")) {
                status = "Pending";
            }
            boolean b = chargeService.insertCharge(charge, status);
            if(b) {
                return "redirect:../charges/makerList";
            }
            else {
                return "views/charge/chargeDefinition";
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
                                @RequestParam("action") String action) {
        String status = null;
        if(action.equalsIgnoreCase("approve")) {
            status = "Approved";
        }else if(action.equalsIgnoreCase("reject")) {
            status = "Rejected";
        }
        chargeService.updateStatus(chargeCode, status);
        return "redirect:/charges/checkerList";
    }

    @RequestMapping(value = {"/delete/{chargeCode}"})
    @PreAuthorize("hasRole('MAKER')")
    public String deleteCharge(@PathVariable("chargeCode") String chargeCode, Model model) {
        boolean deleteStatus = chargeService.deleteCharge(chargeCode);
        model.addAttribute("deleteStatus", deleteStatus);
        return "redirect:/charges/makerList";
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
