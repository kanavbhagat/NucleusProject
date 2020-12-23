package com.nucleus.customerservice.repaymentschedulereport.controller;

import com.nucleus.customerservice.repaymentschedulereport.service.RepaymentScheduleReportService;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class RepaymentScheduleSearchController {

    @Autowired
    private RepaymentScheduleReportService repaymentScheduleService;

    static LocalDate returnDate(String date) {
        LocalDate dt = LocalDate.parse(date);
        return dt;
    }

    @GetMapping("/getRepaymentScheduleReportSearchPage")
    public String showRepaymentScheduleForm()
    {
        return "views/customerservice/RepaymentScheduleReportSearch";
    }

    @RequestMapping(value = "/getRepaymentScheduleReport", method = RequestMethod.GET)
    public ModelAndView showRepaymentScheduleSubmit(@RequestParam("appNo") String appNo, Model model)
    {
        int loanApplicationNumber = Integer.parseInt(appNo);
        List<RepaymentSchedule> rslist=repaymentScheduleService.getRepaymentScheduleReport(loanApplicationNumber);
        if(rslist==null){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/customerservice/InvalidLoanAppPage");
            mv.addObject("Message","loanApplicationId NOT found");
            mv.addObject("loanApplicationNumber",loanApplicationNumber);
            return mv;
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/customerservice/RepaymentScheduleReport");
        mv.addObject("rslist",rslist);
        return mv;
    }
}
