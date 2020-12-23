package com.nucleus.loanapplications.controller;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class LoanApplicationViewController {

    @Autowired
    LoanApplicationService loanApplicationService;

    @PreAuthorize("hasRole ('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value="/loanApplication")
    public ModelAndView loanApplicationView(){
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanApplications");
        modelAndView.addObject("loanApplications",loanApplicationService.getAllLoanApplicationsList());
        return modelAndView;

    }

    @PreAuthorize("hasRole ('ROLE_MAKER')")
    @GetMapping(value="/loanApplication/delete")
    public ModelAndView deleteLoanApplication(@RequestParam(value="loanApplicationNumber", required=true) String loanApplicationNumber,
                                              Model model){
        loanApplicationService.deleteLoanApplicationId(Integer.parseInt(loanApplicationNumber));
        ModelAndView modelAndView =new ModelAndView("views/loanapplication/loanApplicationDeleted");
        modelAndView.addObject("loanApplicationNumber",loanApplicationNumber);
        return modelAndView;
    }
    @GetMapping(value = "loanApplication/edit")
    @PreAuthorize("hasRole ('ROLE_MAKER')")
    public ModelAndView editLoanApplication(@RequestParam(value = "loanApplicationNumber",required = true) String loanApplicationNumber, Model model){
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(Integer.parseInt(loanApplicationNumber));
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanInformationChecker");
        modelAndView.addObject("loanApplicationNumber",loanApplications.getLoanApplicationNumber());
        modelAndView.addObject("loanApplication",loanApplications);
        return modelAndView;
    }

    @PreAuthorize("hasRole ('ROLE_CHECKER')")
    @GetMapping(value = "loanApplication/check")
    public ModelAndView getCheckUrl(@RequestParam(value = "loanApplicationNumber",required = true) String loanApplicationNumber, Model model){
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(Integer.parseInt(loanApplicationNumber));
        ModelAndView modelAndView = new ModelAndView("views/loanApplication/loanApplicationChecker");
        modelAndView.addObject("loanApplication",loanApplications);
        return modelAndView;
    }

}
