package com.nucleus.loanclosurebod.controller;


import com.nucleus.loanclosurebod.service.LoanClosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LoanClosureBod")
public class LoanClosureController {

    @Autowired
    LoanClosureService loanClosureService;

    public LoanClosureService getLoanClosureService(){
        return loanClosureService;
    }
    public void setLoanClosureService(LoanClosureService loanClosureService){
        this.loanClosureService = loanClosureService;
    }

//    @RequestMapping("/UpdateStatus")
//    public void updateStatus(){
//        loanClosureService.addDummyData();
//
//    }

    @RequestMapping("/UpdateStatus")
    public void loanClosureBod(){
        loanClosureService.loanClosureBod();
    }
}