package com.nucleus.customerservice.repaymentschedulereport.controller;

import com.nucleus.customerservice.repaymentschedulereport.service.RepaymentScheduleReportService;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String showRepaymentScheduleForm(ModelMap map)
    {
        RepaymentSchedule repaymentSchedule =new RepaymentSchedule();
        map.put("repaymentSchedule", repaymentSchedule);
        return "views/customerservice/RepaymentScheduleReportSearch";
    }

    @PostMapping("/getRepaymentScheduleReportSearchPage")
    public ModelAndView showRepaymentScheduleSubmit(@Valid @ModelAttribute RepaymentSchedule repaymentSchedule,
                                                    BindingResult result)
    {
        int loanApplicationNumber= repaymentSchedule.getLoanApplicationNumber().getLoanApplicationNumber();
        List<RepaymentSchedule> rslist=repaymentScheduleService.getRepaymentScheduleReport(loanApplicationNumber);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/customerservice/RepaymentScheduleReport");
        mv.addObject("rslist",rslist);
        return mv;
    }
}
