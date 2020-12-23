package com.nucleus.receipt.controller;

import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceiptSearchController {

    @Autowired
    ReceiptService receiptService;

    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/receiptSearch" })
    public ModelAndView receiptSearch() {
        return new ModelAndView("views/receipt/receiptSearch");
    }


    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @PostMapping(value = {"/receiptSearchResults"})
    public ModelAndView getReceipt(@RequestParam(name="receiptType", required = true) String receiptType,
                                   @RequestParam(name="receiptBasis", required = false) String receiptBasis,
                                   @RequestParam(name = "loanAccount", required = false) Integer loanAccountNo,
                                   @RequestParam(name = "receiptNo", required = false) Integer receiptNo) {

        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearchResult");

        List<Object> listReceipts = receiptService.receiptSearch(receiptType, receiptBasis, loanAccountNo, receiptNo);
        List<Receipt> receiptList = new ArrayList<>(listReceipts.size());
        for(Object o : listReceipts){
            receiptList.add((Receipt) o);
        }
        modelAndView.addObject("receiptList", receiptList);
        return modelAndView;
    }
}

