package com.nucleus.receipt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReceiptBOD {

    @GetMapping(value = {"/receiptBOD" })
    public ModelAndView getReceiptBOD() {
        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptBOD");
        return modelAndView;
    }
}
