package com.nucleus.receipt.controller;


import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import com.nucleus.receipt.service.ReceiptValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class NewReceiptController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    LoanApplicationService loanApplicationService;

    @GetMapping(value = {"/newReceipt" })
    public ModelAndView receiptDetails(){

        ModelAndView modelAndView = new ModelAndView("views/receipt/newReceiptCreation");
        Receipt receipt = new Receipt();
        modelAndView.addObject("receipt", receipt);
        return modelAndView;
    }


    @PostMapping(value = {"/registerReceipt"})
    public ModelAndView addReceipt(@Valid @ModelAttribute("receipt") Receipt receipt, BindingResult result){
        ModelAndView modelAndView=new ModelAndView();
        new ReceiptValidator().validate(receipt, result);

        if(result.hasErrors()){
            modelAndView.setViewName("views/receipt/newReceiptCreation");
            return modelAndView;
        }

        modelAndView.setViewName("views/receipt/receiptSuccess");

        receipt.setReceiptStatus("Pending");

        Integer id = Integer.parseInt(receipt.getLoanApplicationValue());
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(id);
        receipt.setLoanApplicationNumber(loanApplications);

        Boolean success = receiptService.registerReceipt(receipt);

        if(success){
            modelAndView.addObject("message", "Receipt Creation was Successful");
            return modelAndView;
        }

        modelAndView.addObject("message", "Receipt Creation Failed");
        return modelAndView;
    }


}
