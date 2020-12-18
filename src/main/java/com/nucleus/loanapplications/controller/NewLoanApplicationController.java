package com.nucleus.loanapplications.controller;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.NewLoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewLoanApplicationController {

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @GetMapping(value = "/newLoanApplication")
    public ModelAndView newLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanApplication/newLoanApplication");
        return modelAndView;
    }

    @PostMapping(value = "/newLoanApplication")
    public ModelAndView createNewLoanApplication(LoanApplications loanApplications, BindingResult result){
        newLoanApplicationService.addLoanApplication(loanApplications);
        ModelAndView modelAndView = new ModelAndView("view/loanApplocation/notDecide");////// TO:DO
        return modelAndView;
    }
}
