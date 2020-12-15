package com.nucleus.charge.controllers;


import com.nucleus.charge.model.NewCharge;
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

    @GetMapping
    public String showForm(ModelMap model) {
        NewCharge charge = new NewCharge();
        model.put("newChargeData", charge);
        System.out.println("Welcome");
        List<String> eventList = new ArrayList<>();
        eventList.add("ProcessingFees");
        eventList.add("InstallmentPrincipalComponent");
        model.put("eventList",eventList);
        return ("chargeUI/chargeDefinition");
    }

    @PostMapping
    public String onSubmit(@Valid @ModelAttribute("newChargeData") NewCharge charge, BindingResult result) {
        if(result.hasErrors()) {
            return "chargeUI/chargeDefinition";
        }
        else {
            return "LoginSuccess";
        }
    }


    /*@ModelAttribute("eventList")
    public List<String> populateEventList() {
        List<String> eventList = new ArrayList<>();
        eventList.add("ProcessingFees");
        eventList.add("InstallmentPrincipalComponent");
        return eventList;
    }*/
}
