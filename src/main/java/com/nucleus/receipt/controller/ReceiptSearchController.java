package com.nucleus.receipt.controller;

import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@RequestMapping("receipt")
public class ReceiptSearchController {

    @Autowired
    ReceiptService receiptService;

    @PostMapping(value = {"/receiptSearch" })
    public ModelAndView receiptSearch() {
        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearch");
        List<Receipt> receiptList = receiptService.getReceipt();
        modelAndView.addObject("receipts", receiptList);
        modelAndView.setViewName("views/receipt/receiptSearchResult");
        return modelAndView;
    }

}

