package com.nucleus.repaymentschedule.controller;


import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentschedule.service.RepaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class RepaymentScheduleController {

    @Autowired
    private RepaymentScheduleService repaymentScheduleService;

    static LocalDate returnDate(String date) {
        LocalDate dt = LocalDate.parse(date);
        return dt;
    }

    @GetMapping("/addRepaymentReport")
    public void addRepaymentSchedule()
    {

        LoanApplications loanApplication =new LoanApplications();
        loanApplication.setInstallmentDueDate(returnDate("2020-01-03"));
        loanApplication.setLoanAmountRequested(1000000);
        loanApplication.setLoanApplicationNumber(2);
        //loanApplication.setProductCode("p1");
        loanApplication.setTenure(5);
        loanApplication.setRate(10.75);

        int r=repaymentScheduleService.addRepaymentSchedule(loanApplication);
        return;

    }
}
