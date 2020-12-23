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


/**
 * Controller serving the receipt search page.
 */
@Controller
public class ReceiptSearchController {

    @Autowired
    ReceiptService receiptService;


    /**
     * <p> Get mapping to serve the receipt search home page. </p>
     * @return returns modelAndView of the receipt search page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/receiptSearch" })
    public ModelAndView receiptSearch() {
        return new ModelAndView("views/receipt/receiptSearch");
    }


    /**
     * <p> Post mapping for the receipt search page. Conducts a search based on 4 params and returns a model and view
     * of either the search result page, or the error page if there were no results. </p>
     * @param String receiptType required
     * @param String receiptBasis optional
     * @param Integer loanAccountNo optional
     * @param Integer receiptNo optional
     * @return returns modelAndView with results if operation was successful, else the error page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @PostMapping(value = {"/receiptSearchResults"})
    public ModelAndView getReceipt(@RequestParam(name="receiptType", required = true) String receiptType,
                                   @RequestParam(name="receiptBasis", required = false) String receiptBasis,
                                   @RequestParam(name = "loanAccount", required = false) Integer loanAccountNo,
                                   @RequestParam(name = "receiptNo", required = false) Integer receiptNo) {

        ModelAndView modelAndView = new ModelAndView("views/receipt/receiptSearchResult");

        List<Receipt> receiptList = receiptService.receiptSearch(receiptType, receiptBasis, loanAccountNo, receiptNo);
        System.out.println(receiptList.size());
        modelAndView.addObject("receiptList", receiptList);

        if(receiptList.isEmpty()){
            modelAndView.setViewName("views/receipt/receiptError");
            modelAndView.addObject("messageHeader", "No results found");
            modelAndView.addObject("messageBody", "No matching results were found for your criteria. " +
                                    "Please try again.");
            modelAndView.addObject("receiptNumber", "N/A");
        }
        return modelAndView;
    }
}

