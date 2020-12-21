package com.nucleus.receipt.controller;

import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class receiptCheckerController {


    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = {"/receiptChecker" })
    public ModelAndView receiptChecker(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("receipts",receiptService.getReceiptList());
        mv.setViewName("views/receipt/receiptChecker");
        return mv;
    }


}
