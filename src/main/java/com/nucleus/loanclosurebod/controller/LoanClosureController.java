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

import org.springframework.web.servlet.ModelAndView;

/**
 * The LoanClosureController class starts the Loan Closure BOD process
 * by calling the required method of the Loan Closure Service Class with the
 * help of an object of LoanClosureService Interface. It also defines the getter
 * and setter methods for the mentioned object.
 */


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

    /**
     * loanClosureBod is the main method which is triggered. It calls the
     * loaClosureBod method of Service class with the help of its object
     * declared above.
     */
    @RequestMapping("/loanClosureBod")
    public ModelAndView loanClosureBod(){
        int closedLoans = loanClosureService.loanClosureBod();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ClosedLoans", closedLoans);
        modelAndView.setViewName("views/loanclosurebod/viewClosedLoanCount");
        return modelAndView;
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