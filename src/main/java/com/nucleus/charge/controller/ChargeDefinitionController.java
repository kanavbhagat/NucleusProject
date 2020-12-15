package com.nucleus.charge.controller;


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
        return ("views/charge/chargeDefinition");
    }

    @PostMapping
    public String onSubmit(@Valid @ModelAttribute("newChargeData") NewCharge charge, BindingResult result) {
        if(result.hasErrors()) {
            return "views/charge/chargeDefinition";
        }
        else {
            return "views/charge/LoginSuccess";
        }
    }


    @ModelAttribute("eventList")
    public List<String> populateEventList() {
        List<String> eventList = new ArrayList<>();
        eventList.add("Processing Fees");
        return eventList;
    }

    @ModelAttribute("chargeTypeList")
    public List<String> populateChargeType() {
        List<String> chargeList = new ArrayList<>();
        chargeList.add("Amount Based");
        return chargeList;
    }
}
