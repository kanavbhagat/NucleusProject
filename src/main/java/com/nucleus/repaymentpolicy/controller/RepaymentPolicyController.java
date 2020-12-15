package com.nucleus.repaymentpolicy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RepaymentPolicyController {

    @RequestMapping("/showRepaymentPolicy")
    public ModelAndView showRepaymentPolicyList()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/repaymentPolicyMaker");
        return mv;

    }
}