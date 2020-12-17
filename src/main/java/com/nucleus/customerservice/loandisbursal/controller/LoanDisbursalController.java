package com.nucleus.customerservice.loandisbursal.controller;

import com.nucleus.customerservice.loandisbursal.exceptions.CustomerNotFoundException;
import com.nucleus.customerservice.loandisbursal.exceptions.LoanApplicationNotFoundException;
import com.nucleus.customerservice.loandisbursal.model.LoanApplication;
import com.nucleus.customerservice.loandisbursal.service.LoanDisbursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class LoanDisbursalController {
    @Autowired
    private LoanDisbursalService loanDisbursalService;

    @GetMapping(path = "/loandisbursalDetails/{loanApplicationNumber}")
    public LoanApplication getLoanDisbursal(@PathVariable("loanApplicationNumber") int loanApplicationId){
        LoanApplication loanApplication= loanDisbursalService.getLoanDetails(loanApplicationId);
        if(loanApplication==null){
            throw new LoanApplicationNotFoundException("loanApplication NOT found: "+ loanApplicationId);
        }
        return loanApplication;
    }

    @GetMapping(path = "/customerloandisbursal/{customerId}")
    public Set<LoanApplication> getLoanDisbursalByCustomerId(@PathVariable("customerId") String customerId){
        Set<LoanApplication> loanApplications=loanDisbursalService.getCustomerLoanDetails(customerId);
        if(loanApplications.isEmpty()){
            throw new CustomerNotFoundException("CustomerCode NOT found: "+ customerId);
        }
        return loanApplications;
    }

}
