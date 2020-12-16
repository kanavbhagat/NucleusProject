package com.nucleus.receipt.controller;


import com.nucleus.receipt.model.Receipt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewReceiptController {

    @GetMapping(value = {"/newReceipt" })
    public ModelAndView receiptDetails(){
        //ModelAndView modelAndView=new Mo
        ModelAndView modelAndView = new ModelAndView("views/receipt/newReceiptCreation");
        Receipt receipt = new Receipt();
        modelAndView.addObject("receipt",receipt);
        return modelAndView;
    }

}
