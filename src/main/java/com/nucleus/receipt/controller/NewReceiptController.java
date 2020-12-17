package com.nucleus.receipt.controller;


import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewReceiptController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = {"/newReceipt" })
    public ModelAndView receiptDetails(){

        ModelAndView modelAndView = new ModelAndView("views/receipt/newReceiptCreation");
        Receipt receipt = new Receipt();
        modelAndView.addObject("receipt",receipt);
        return modelAndView;
    }

    @PostMapping(value = {"/add"})
    public ModelAndView addReceipt(@ModelAttribute Receipt receipt){
        ModelAndView modelAndView=new ModelAndView();
        System.out.println(receipt);
        System.out.println(receiptService.registerReceipt(receipt));
        //modelAndView.se
        return modelAndView;
    }

}
