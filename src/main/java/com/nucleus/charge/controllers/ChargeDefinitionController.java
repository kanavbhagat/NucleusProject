package com.nucleus.charge.controllers;


import com.nucleus.charge.model.NewCharge;
import com.nucleus.charge.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/newChargeCreation")
public class ChargeDefinitionController {

    @Autowired
    ChargeService chargeService;

    @GetMapping
    public String showForm(ModelMap model) {
        NewCharge charge = new NewCharge();
        model.put("newChargeData", charge);
        return ("views/charge/chargeDefinition");
    }

    @PostMapping
    public String onSubmit(@Valid @ModelAttribute("newChargeData") NewCharge charge, BindingResult result) {
        if(result.hasErrors()) {
            return "views/charge/chargeDefinition";
        }
        else {
            boolean b = chargeService.insertCharge(charge);
            if(b) {
                return "redirect:charges";
            }
            else {
                return "views/charge/chargeDefinition";
            }

        }
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
}
