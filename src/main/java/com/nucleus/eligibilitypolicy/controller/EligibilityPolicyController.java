package com.nucleus.eligibilitypolicy.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String newEligibilityPolicy(Model model) {
        EligibilityPolicy eligibilityPolicy = new EligibilityPolicy();
        //List<EligibilityParameter> eligibilityParameterList = eligibilityPolicyService.getParameters();

        List<EligibilityParameter> eligibilityParameterList = new ArrayList<>();
        EligibilityParameter eligibilityParameter = new EligibilityParameter();
        eligibilityParameter.setParameterCode("101");
        eligibilityParameter.setParameterDescription("AAA");
        eligibilityParameter.setParameterName("test 1");
        eligibilityParameterList.add(eligibilityParameter);
        eligibilityParameter = new EligibilityParameter();
        eligibilityParameter.setParameterCode("102");
        eligibilityParameter.setParameterDescription("BBB");
        eligibilityParameter.setParameterName("test 2");
        eligibilityParameterList.add(eligibilityParameter);

        model.addAttribute("eligibilityPolicy", eligibilityPolicy);
        model.addAttribute("eligibilityParameterList", eligibilityParameterList);
        return "views/eligibilitypolicies/newEligibilityPolicy";
    }

    @PostMapping(value = {"/added"})
    public String showEligibilityPolicy(@ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy, Model model) {
        eligibilityPolicy.setParameterCode("11");
        boolean insertStatus = eligibilityPolicyService.insertEligibilityPolicy(eligibilityPolicy);
        model.addAttribute("insertStatus", insertStatus);
        return "views/eligibilitypolicies/success";
    }
}
