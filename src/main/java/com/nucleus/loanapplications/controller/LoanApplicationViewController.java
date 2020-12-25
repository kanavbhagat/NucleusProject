package com.nucleus.loanapplications.controller;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.repaymentschedule.service.RepaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

@RestController
public class LoanApplicationViewController {

    @Autowired
    private RepaymentScheduleService repaymentScheduleService;


    @Autowired
    LoginDetailsImpl loginDetails;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class , new DateEditor());
    }
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
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanInformationMaker");
        modelAndView.addObject("loanApplication",loanApplications);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(params = {"op=Update"},value = "loanApplication/edit")
    public ModelAndView update(@ModelAttribute("loanApplication") LoanApplications loanApplications,@RequestParam("op") String update,
                               Model model){
        LoanApplications tempLoanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus(tempLoanApplications.getStatus());
        loanApplications.setAuthorizedDate(tempLoanApplications.getAuthorizedDate());
        loanApplications.setCustomerCode(tempLoanApplications.getCustomerCode());
        loanApplications.setCreateDate(tempLoanApplications.getCreateDate());
        loanApplications.setModifiedBy(loginDetails.getUserName());
        loanApplications.setModifiedDate(LocalDate.now());
        loanApplications.setCreatedBy(tempLoanApplications.getCreatedBy());
        loanApplications.setAuthorizedBy(tempLoanApplications.getAuthorizedBy());
        loanApplications.setLoanApplicationNumber(tempLoanApplications.getLoanApplicationNumber());

        loanApplicationService.updateLoanApplication(loanApplications);
        ModelAndView modelAndView = new ModelAndView("redirect:/loanApplication");

        return modelAndView;
    }
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(params = {"op=Update and Request"},value = "loanApplication/edit")
    public ModelAndView updateAndRequest(@ModelAttribute("loanApplication") LoanApplications loanApplications,@RequestParam("op") String upAndreq,
                               Model model){
        LoanApplications tempLoanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus(tempLoanApplications.getStatus());
        loanApplications.setAuthorizedDate(tempLoanApplications.getAuthorizedDate());
        loanApplications.setCustomerCode(tempLoanApplications.getCustomerCode());
        loanApplications.setCreateDate(tempLoanApplications.getCreateDate());
        loanApplications.setModifiedBy(loginDetails.getUserName());
        loanApplications.setModifiedDate(LocalDate.now());
        loanApplications.setCreatedBy(tempLoanApplications.getCreatedBy());
        loanApplications.setAuthorizedBy(tempLoanApplications.getAuthorizedBy());
        loanApplications.setLoanApplicationNumber(tempLoanApplications.getLoanApplicationNumber());
        loanApplications.setStatus("PENDING");
        loanApplicationService.updateLoanApplication(loanApplications);
        ModelAndView modelAndView = new ModelAndView("redirect:/loanApplication");

        return modelAndView;
    }

    @PreAuthorize("hasRole ('ROLE_CHECKER')")
    @GetMapping(value = "loanApplication/check")
    public ModelAndView getCheckUrl(@RequestParam(value = "loanApplicationNumber",required = true) String loanApplicationNumber, Model model){
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(Integer.parseInt(loanApplicationNumber));
        System.out.println("----------------------+"+loanApplications.getCustomerCode().getCustomerCode()+"----------------------");

        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanApplicationChecker");
        modelAndView.addObject("loanApplicationNumber",loanApplicationNumber);
        modelAndView.addObject("loanApplication",loanApplications);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @PostMapping(params = {"op=approve"},value = "loanApplication/check")
    public ModelAndView approve(@ModelAttribute("loanApplication") LoanApplications loanApplications,
                               @RequestParam("op") String approve,
                                Model model){

        loanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus("APPROVED");
        System.out.println("----------------------+"+loanApplications.getCustomerCode().getCustomerCode()+"----------------------");
        loanApplications.setAuthorizedBy(loginDetails.getUserName());
        loanApplications.setAuthorizedDate(LocalDate.now());
        loanApplicationService.updateLoanApplication(loanApplications);
        repaymentScheduleService.addRepaymentSchedule(loanApplications);
        ModelAndView modelAndView = new ModelAndView("redirect:/loanApplication");

        return modelAndView;

    }
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @PostMapping(params = {"op=reject"},value = "loanApplication/check")
    public ModelAndView reject(@ModelAttribute("loanApplication") LoanApplications loanApplications,@RequestParam("op") String reject,
                                Model model){
        loanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus("REJECTED");
        loanApplications.setAuthorizedBy(loginDetails.getUserName());
        loanApplications.setAgreementDate(LocalDate.now());
        loanApplicationService.updateLoanApplication(loanApplications);
        ModelAndView modelAndView = new ModelAndView("redirect:/loanApplication");

        return modelAndView;

    }


}
