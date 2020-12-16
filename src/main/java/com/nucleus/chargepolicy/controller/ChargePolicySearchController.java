package com.nucleus.chargepolicy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nucleus.chargepolicy.model.ChargePolicy;
import com.nucleus.chargepolicy.service.ChargePolicySearchService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chargePolicySearch.do")
public class ChargePolicySearchController {


    @GetMapping
    public String showTable(ModelMap modelMap){
        List<ChargePolicy> chargePolicyList = new ChargePolicySearchService().getChargePolicyList();
        ChargePolicy chargePolicy = new ChargePolicy();
        modelMap.put("chargePolicy",chargePolicy);


        System.out.println(chargePolicyList.get(0).getChargeCode());
        ChargePolicy chargePolicy1 = new ChargePolicy();
        chargePolicy1.setChargePolicyCode("101");
        chargePolicy1.setChargePolicyDesc("sdfsdf");
        chargePolicy1.setChargePolicyName(" asdas");
        chargePolicyList.add(chargePolicy1);

        ChargePolicy chargePolicy2 = new ChargePolicy();
        chargePolicy2.setChargePolicyCode("102");
        chargePolicy2.setChargePolicyDesc("sdfsdf");
        chargePolicy2.setChargePolicyName(" asdas");
        chargePolicyList.add(chargePolicy2);

        ChargePolicy chargePolicy3 = new ChargePolicy();
        chargePolicy3.setChargePolicyCode("103");
        chargePolicy3.setChargePolicyDesc("sdfsdf");
        chargePolicy3.setChargePolicyName(" asdas");
        chargePolicyList.add(chargePolicy3);

        modelMap.put("chargePolicyList",chargePolicyList);
        //odelMap.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/chargePolicySearch";
    }
}
