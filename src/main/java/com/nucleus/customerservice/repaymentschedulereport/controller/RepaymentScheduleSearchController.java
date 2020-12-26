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



    /**
     * retrieves RepaymentScheduleReportSearch JSP page to get loanApplicationNumber.
     * @return ModelAndView returns the view containing RepaymentScheduleReportSearch.jsp
     */
    @GetMapping("/getRepaymentScheduleReportSearchPage")
    public String showRepaymentScheduleForm()
    {
        return "views/customerservice/RepaymentScheduleReportSearch";
    }


    /**
     * Handles and retrieves Repayment Schedule and show them in a JSP page RepaymentScheduleReport.
     * Displays a failure on InvalidLoanAppPage JSP page if failure occurs due to non-existing loanApplicationNumber.
     * @param appNo It is the loan application number whose Repaymentschedule is to be displayed
     * @param model to view the page
     * @return ModelAndView returns the view containing RepaymentScheduleReport.jsp
     */
    @RequestMapping(value = "/getRepaymentScheduleReport", method = RequestMethod.GET)
    public ModelAndView showRepaymentScheduleSubmit(@RequestParam("appNo") String appNo)
    {
        int loanApplicationNumber = Integer.parseInt(appNo);
        List<RepaymentSchedule> rslist=repaymentScheduleService.getRepaymentScheduleReport(loanApplicationNumber);
        if(rslist.size()==0){
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
