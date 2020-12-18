package com.nucleus.charge.controllers;

import com.nucleus.charge.model.NewCharge;
import com.nucleus.charge.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChargeDisplayController {

    @Autowired
    ChargeService chargeService;

    @GetMapping("/charges")
    public String chargeDisplay(ModelMap model) {
        List<NewCharge> chargeList = chargeService.getChargeList();
        model.put("chargeList",chargeList);
        return "views/charge/chargeScreen";
    }

}
