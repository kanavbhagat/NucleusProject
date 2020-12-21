package com.nucleus.loanapplications.controller;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.NewLoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewLoanApplicationController {

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @GetMapping(value = "/newLoanApplication")
    public ModelAndView addNewLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanapplication/loanInformation");
        modelAndView.addObject("newLoanApplication",new LoanApplications());
        return modelAndView;
    }
}
