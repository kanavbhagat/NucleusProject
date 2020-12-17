package com.nucleus.chargepolicy.controller;


import com.nucleus.chargepolicy.dao.ChargePolicyDao;
import com.nucleus.chargepolicy.service.ChargePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.web.servlet.ModelAndView;
//import spring.service.ChargePolicyService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chargePolicy")
public class ChargePolicyController {

    @Autowired
    ChargePolicyService chargePolicyService;

    public ChargePolicyService getChargePolicyService() {
        return chargePolicyService;
    }

    public void setChargePolicyService(ChargePolicyService chargePolicyService) {
        this.chargePolicyService = chargePolicyService;
    }



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
       this.chargePolicyService.insert(chargePolicy);
        return "views/chargepolicy/Success";
    }

    @GetMapping(value = {"/searchScreen"})
    public String showAllEligibilityPolicies(ModelMap modelMap) {
        List<ChargePolicy> chargePolicyList = new ArrayList<ChargePolicy>();
        chargePolicyList.addAll(this.chargePolicyService.getPolicyList());
        List<String> chargeCodeList = this.chargePolicyService.getChargeCodes();
        System.out.println("In controller with " + chargeCodeList);
        ChargePolicy chargePolicy = new ChargePolicy();
        modelMap.put("chargePolicy",chargePolicy);
        modelMap.put("chargePolicyList",chargePolicyList);
        //odelMap.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/chargePolicySearch";
    }
    @GetMapping(value = {"/getCharge"})
    public void getCharge(ModelMap modelMap) {
        System.out.println("in getCharge");
       this.chargePolicyService.getCharge("1010");
    }
}
