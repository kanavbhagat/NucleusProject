package com.nucleus.customerservice.loandisbursal.controller;

import com.nucleus.customerservice.loandisbursal.exceptions.CustomerNotFoundException;
import com.nucleus.customerservice.loandisbursal.exceptions.LoanApplicationNotFoundException;
import com.nucleus.customerservice.loandisbursal.service.LoanDisbursalService;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class LoanDisbursalController {
    @Autowired
    private LoanDisbursalService loanDisbursalService;

    @GetMapping(value = {"/loanDisbursalForm" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/loan disbursal/LoanDisbursalForm");
        return modelAndView;
    }

    @GetMapping(path = "/loandisbursalDetail/{loanApplicationNumber}")
    public LoanApplications getLoanDisbursal(@PathVariable("loanApplicationNumber") int loanApplicationId){
        LoanApplications loanApplication= loanDisbursalService.getLoanDetails(loanApplicationId);
        if(loanApplication==null){
            throw new LoanApplicationNotFoundException("loanApplication NOT found: "+ loanApplicationId);
        }
        return loanApplication;
    }

    @GetMapping(path = "/customerloandisbursals/{customerId}")
    public List<LoanApplications> getLoanDisbursalByCustomerId(@PathVariable("customerId") String customerId){
        List<LoanApplications> loanApplications=loanDisbursalService.getCustomerLoanDetails(customerId);
        if(loanApplications==null){
            throw new CustomerNotFoundException("CustomerCode NOT found: "+ customerId);
        }
        return loanApplications;
    }

    @GetMapping(path = "/loandisbursalDetails")
    public ModelAndView getLoanDisbursals(@RequestParam("loanApplicationNumber") int loanApplicationId){
        LoanApplications loanApplication= loanDisbursalService.getLoanDetails(loanApplicationId);
        if(loanApplication==null){
            return new ModelAndView("views/loan disbursal/ErrorPage", "Message", "loanApplicationId NOT found: "+ loanApplicationId);
        }
        return new ModelAndView("views/loan disbursal/LoanDisbursalDetails", "loanApp", loanApplication);
    }

    @GetMapping(path = "/customerloandisbursal")
    public ModelAndView getLoanDisbursalsByCustomerId(@RequestParam("customerCode") String customerId){
        List<LoanApplications> loanApplications=null;
        loanApplications = loanDisbursalService.getCustomerLoanDetails(customerId);
        if(loanApplications==null){
            return new ModelAndView("views/loan disbursal/ErrorPage", "Message", "CustomerCode NOT found: "+ customerId);
        }
        return new ModelAndView("views/loan disbursal/CustomerLoanDisbursal", "allLoanApps", loanApplications);
    }

}
