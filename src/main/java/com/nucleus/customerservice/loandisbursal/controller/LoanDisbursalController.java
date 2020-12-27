package com.nucleus.customerservice.loandisbursal.controller;

import com.nucleus.customerservice.loandisbursal.service.LoanDisbursalService;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class LoanDisbursalController {
    @Autowired
    private LoanDisbursalService loanDisbursalService;

    /**
     * controller for LoanDisbursalForm View
     * @return view of LoanDisbursalForm
     */
    @GetMapping(value = {"/loanDisbursalForm" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/loan disbursal/LoanDisbursalForm");
        return modelAndView;
    }

    /**
     * controller for LoanDisbursalDetails View
     * @return ModelAndView of loan Details
     */
    @GetMapping(path = "/loandisbursalDetails")
    public ModelAndView getLoanDisbursals(@RequestParam("loanApplicationNumber") int loanApplicationId){
        LoanApplications loanApplication= loanDisbursalService.getLoanDetails(loanApplicationId);
        if(loanApplication==null){
            return new ModelAndView("views/loan disbursal/ErrorPage", "Message", "Loan Application Number NOT found: "+ loanApplicationId);
        }
        return new ModelAndView("views/loan disbursal/LoanDisbursalDetails", "loanApp", loanApplication);
    }

    /**
     * controller for LoanDisbursalDetails View
     * @return ModelAndView of All loan Details of a customer
     */
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
