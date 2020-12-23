package com.nucleus.receipt.controller;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import com.nucleus.receipt.service.ReceiptValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


/**
 * <p> controller for new receipt creation </p>
 */
@Controller
public class NewReceiptController {

    @Autowired
    ReceiptService receiptService;

    @Autowired
    LoanApplicationService loanApplicationService;


    /**
     * <p> Get mapping for the new receipt creation page. returns a model and view with an empty receipt object attached
     * for the receipt creation form.</p>
     * @return modelAndView for new receipt creation.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/newReceipt" })
    public ModelAndView receiptDetails(){

        ModelAndView modelAndView = new ModelAndView("views/receipt/newReceiptCreation");
        Receipt receipt = new Receipt();
        modelAndView.addObject("receipt", receipt);
        return modelAndView;
    }

    /**
     * <p> Post mapping for creating a new receipt. Retrieves the model receipt object from the receipt creation
     * form and saves it into the database. </p>
     * @param Receipt receipt the model object created from the form.
     * @param BindingResult result the binding result for errors.
     * @return either the success or error modelAndView depending on whether receipt creation was successful.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = {"/registerReceipt"})
    public ModelAndView addReceipt(@Valid @ModelAttribute("receipt") Receipt receipt, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();

        // extra validation so that hibernate validations continue to work.
        new ReceiptValidator().validate(receipt, result);

        if(result.hasErrors()){
            modelAndView.setViewName("views/receipt/newReceiptCreation");
            return modelAndView;
        }

        receipt.setReceiptStatus("Pending");

        Integer id = Integer.parseInt(receipt.getLoanApplicationValue());
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(id);
        receipt.setLoanApplicationNumber(loanApplications);

        Boolean success = receiptService.registerReceipt(receipt);

        if(success){
            modelAndView.setViewName("views/receipt/receiptSuccess");
            modelAndView.addObject("messageHeader", "Receipt Creation was Successful");
            modelAndView.addObject("messageBody", "You successfully created a new receipt");
            modelAndView.addObject("receiptNumber", receipt.getReceiptNo().toString());
            return modelAndView;
        }

        modelAndView.setViewName("views/receipt/receiptError");
        modelAndView.addObject("messageHeader", "Receipt Creation Failed");
        modelAndView.addObject("messageBody", "There was an error creating this receipt. Please try again.");
        modelAndView.addObject("receiptNumber", receipt.getReceiptNo().toString());
        return modelAndView;
    }


}
