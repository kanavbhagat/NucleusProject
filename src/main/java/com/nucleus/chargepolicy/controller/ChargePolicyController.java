package com.nucleus.chargepolicy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.nucleus.chargepolicy.model.ChargePolicy;
import com.nucleus.chargepolicy.service.ChargePolicyService;
//import spring.service.ChargePolicyService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chargePolicy.do")
public class ChargePolicyController {

    @Autowired
    ChargePolicyService chargePolicyService;
    @GetMapping
    public String showForm(ModelMap model){
        ChargePolicy chargePolicy = new ChargePolicy();
        model.put("chargePolicy",chargePolicy);
       // model.put("chargePolicyCode", chargePolicy.getChargePolicyCode());
        List<String> chargeCodeList = new ArrayList<String>();
        chargeCodeList.add("101");
        chargeCodeList.add("102");
        model.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/chargePolicy";
    }

    @PostMapping
    public String getFormData(@ModelAttribute ChargePolicy chargePolicy){
       new ChargePolicyService().insert(chargePolicy);
        return "views/chargepolicy/Success";
    }
}
