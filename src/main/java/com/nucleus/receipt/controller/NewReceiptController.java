package com.nucleus.receipt.controller;


import com.nucleus.loanapplications.service.NewLoanApplicationService;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import com.nucleus.receipt.service.ReceiptValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class NewReceiptController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @Autowired
    ReceiptValidator receiptValidator;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.setValidator(receiptValidator);
        dataBinder.registerCustomEditor(LocalDate.class, new DateEditor());
    }


    @GetMapping(value = {"/newReceipt" })
    public ModelAndView receiptDetails(){

        ModelAndView modelAndView = new ModelAndView("views/receipt/newReceiptCreation");
        Receipt receipt = new Receipt();
        modelAndView.addObject("receipt",receipt);
        return modelAndView;
    }


    @PostMapping(value = {"/registerReceipt"})
    public ModelAndView addReceipt(@Valid @ModelAttribute Receipt receipt, BindingResult result){

        ModelAndView modelAndView=new ModelAndView();
        //receipt.getReceiptNo()
        System.out.println(receipt.getLoanApplicationValue());
        if(result.hasErrors()){
            //modelAndView.addObject("error", "Number Exception");
            modelAndView.setViewName("views/receipt/newReceiptCreation");
        }
        //System.out.println(receipt.getLoanApplicationValue());
        //receipt.setLoanApplicationNumber(newLoanApplicationService.);
        else{
            modelAndView.setViewName("views/receipt/receiptSearch");
        }
        //receiptService.registerReceipt(receipt);
        //modelAndView.se
        return modelAndView;
    }


}
