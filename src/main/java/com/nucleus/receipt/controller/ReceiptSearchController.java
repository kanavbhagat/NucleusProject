package com.nucleus.receipt.controller;

import com.nucleus.product.model.Product;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("receipt")
public class ReceiptSearchController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = {"/receiptSearch" })
    public ModelAndView receiptSearch() {
        return new ModelAndView("views/receipt/receiptSearch");
    }


    @PostMapping(value = {"/receiptSearchResults"})
    public ModelAndView getReceipt(@RequestParam(name="receiptType", required = true) String receiptType,
                                   @RequestParam(name="receiptBasis", required = false) String receiptBasis,
                                   @RequestParam(name = "loanAccount", required = false) Integer loanAccountNo,
                                   @RequestParam(name = "receiptNo", required = false) Integer receiptNo) {

        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearchResult");

        System.out.println(receiptType + " " + receiptBasis + " " + loanAccountNo + " " + receiptNo);

        List<Object> listReceipts = receiptService.receiptSearch(receiptType, receiptBasis, loanAccountNo, receiptNo);
        List<Receipt> receiptList = new ArrayList<>(listReceipts.size());
        for(Object o : listReceipts){
            receiptList.add((Receipt) o);
        }
        modelAndView.addObject("receiptList", receiptList);
        return modelAndView;
    }
}

