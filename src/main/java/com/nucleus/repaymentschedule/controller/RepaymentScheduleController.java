package com.nucleus.repaymentschedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RepaymentScheduleController {

    @RequestMapping("/showRepaymentSchedule")
    public ModelAndView showRepaymentScheduleList()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/views/repaymentschedule/repaymentschedulereport");
        return mv;
    }
}
