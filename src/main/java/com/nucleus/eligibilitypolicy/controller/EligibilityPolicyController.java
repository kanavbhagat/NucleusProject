package com.nucleus.eligibilitypolicy.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("eligibilityPolicy")
public class EligibilityPolicyController {

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;

    public EligibilityPolicyService getEligibilityPolicyService() {
        return eligibilityPolicyService;
    }

    public void setEligibilityPolicyService(EligibilityPolicyService eligibilityPolicyService) {
        this.eligibilityPolicyService = eligibilityPolicyService;
    }

    @GetMapping(value = {"/new"})
    public String newEligibilityPolicy(ModelMap model) {
        EligibilityPolicy eligibilityPolicy = new EligibilityPolicy();

        //eligibilityPolicyService.addParameters();
        List<EligibilityParameter> eligibilityParameterList = eligibilityPolicyService.getParameters();
        System.out.println(eligibilityParameterList);
        model.addAttribute("eligibilityPolicy", eligibilityPolicy);
        model.addAttribute("allEligibilityParameterList", eligibilityParameterList);
        return "views/eligibilitypolicies/newEligibilityPolicy";
    }

    @PostMapping(value = {"/added"})
    public String showEligibilityPolicy(@RequestParam("parameter1")String parameterName1, @ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy, Model model) {
        System.out.println(parameterName1);
        boolean insertStatus = eligibilityPolicyService.insertEligibilityPolicy(eligibilityPolicy);
        model.addAttribute("insertStatus", insertStatus);
        return "views/eligibilitypolicies/success";
    }

}
