package com.nucleus.receipt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReceiptSearchController {

    @GetMapping(value = {"/receiptSearch" })
    public ModelAndView receiptSearch() {
        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearch");
        //modelAndView.addObject("products", productService.getProductList());
        return modelAndView;
    }
}

