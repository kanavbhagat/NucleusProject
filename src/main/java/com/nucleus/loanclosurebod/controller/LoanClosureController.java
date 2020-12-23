package com.nucleus.loanclosurebod.controller;


import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.customerservice.loandisbursal.exceptions.CustomerNotFoundException;
import com.nucleus.customerservice.loandisbursal.exceptions.LoanApplicationNotFoundException;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.NewLoanApplicationService;
import com.nucleus.loanclosurebod.service.LoanClosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/main")
public class LoanClosureController {

    @Autowired
    LoanClosureService loanClosureService;

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @Autowired
    NewCustomerService newCustomerService;

    public LoanClosureService getLoanClosureService(){
        return loanClosureService;
    }
    public void setLoanClosureService(LoanClosureService loanClosureService){
        this.loanClosureService = loanClosureService;
    }

//    @RequestMapping("/loanClosureBod")
//    public void updateStatus(){
//        loanClosureService.addDummyData();
//
//    }

    @RequestMapping("/loanClosureBod")
    public void loanClosureBod(){
        loanClosureService.loanClosureBod();
    }

    @GetMapping(value = {"/loanClosureForm" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/loanclosure/LoanClosureForm");
        return modelAndView;
    }

    @GetMapping(path = "/loanClosureDetail/{loanApplicationNumber}")
    public LoanApplications getLoanClosure(@PathVariable("loanApplicationNumber") int loanApplicationId){
        LoanApplications loanApplication= loanClosureService.getLoanDetails(loanApplicationId);
        if(loanApplication==null){
            throw new LoanApplicationNotFoundException("loanApplication NOT found: "+ loanApplicationId);
        }
        return loanApplication;
    }

    @GetMapping(path = "/customerLoanClosures/{customerId}")
    public List<LoanApplications> getLoanClosureByCustomerId(@PathVariable("customerId") String customerId){
        List<LoanApplications> loanApplications= newCustomerService.getCustomerLoanDetails(customerId);
        if(loanApplications==null){
            throw new CustomerNotFoundException("CustomerCode NOT found: "+ customerId);
        }
        return loanApplications;
    }

    @GetMapping(path = "/loanClosureDetails")
    public ModelAndView getLoanClosures(@RequestParam("loanApplicationNumber") int loanApplicationId){
        LoanApplications loanApplication= loanClosureService.getLoanDetails(loanApplicationId);
        if(loanApplication==null){
            return new ModelAndView("views/loanclosure/ErrorPage", "Message", "loanApplicationId NOT found: "+ loanApplicationId);
        }
        return new ModelAndView("views/loanclosure/LoanClosureDetails", "loanApp", loanApplication);
    }

    @GetMapping(path = "/customerLoanClosures")
    public ModelAndView getLoanClosuresByCustomerId(@RequestParam("customerCode") String customerId){
        List<LoanApplications> loanApplications=null;
        loanApplications = loanClosureService.getCustomerLoanDetails(customerId);
        if(loanApplications==null){
            return new ModelAndView("views/loanclosure/ErrorPage", "Message", "CustomerCode NOT found: "+ customerId);
        }
        return new ModelAndView("views/loanclosure/CustomerLoanClosure", "allLoanApps", loanApplications);
    }
}