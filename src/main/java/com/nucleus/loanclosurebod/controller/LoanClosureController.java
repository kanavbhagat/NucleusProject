package com.nucleus.loanclosurebod.controller;

import com.nucleus.loanclosurebod.service.LoanClosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}