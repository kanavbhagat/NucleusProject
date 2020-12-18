package com.nucleus.repaymentpolicy.controller;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class RepaymentPolicyController {

    @Autowired
    RepaymentPolicyService repaymentPolicyService;

    @RequestMapping("/showRepaymentPolicy")
    public ModelAndView showRepaymentPolicyList()
    {
        //repaymentPolicyService.addTempRepaymentPolicy();

        List<RepaymentPolicy> policyList =  repaymentPolicyService.getRepaymentPolicyList();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/repaymentPolicyMaker");
        mv.addObject("policyList",policyList);
        return mv;
    }

    @GetMapping("/editRepaymentPolicy/{policyCode}")
    public String editRepaymentPolicy(@PathVariable String policyCode)
    {
        return "Edit Policy "+ policyCode;
    }

    @GetMapping("/deleteRepaymentPolicy/{policyCode}")
    public String deleteRepaymentPolicy(@PathVariable String policyCode)
    {
        return "Delete Policy "+ policyCode;
    }
}