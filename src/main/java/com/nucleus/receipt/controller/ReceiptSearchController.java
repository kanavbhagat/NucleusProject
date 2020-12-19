package com.nucleus.receipt.controller;

import com.nucleus.product.model.Product;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("receipt")
public class ReceiptSearchController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = {"/receiptSearch" })
    public ModelAndView receiptSearch() {
        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearch");
        return modelAndView;
    }


    @PostMapping(value = {"/get"})
    public ModelAndView getReceipt(@RequestParam("receiptType") String rType, @RequestParam("receiptBasis") String rBasis, @RequestParam("loanAccount") String accountNo, @RequestParam("receiptRef") String rRef) {
        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearchResult");
        List<Receipt> listReceipts = receiptService.getReceipt(rType, rBasis, accountNo, rRef);
        modelAndView.addObject(listReceipts);
        return modelAndView;
    }
}

