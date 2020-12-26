package com.nucleus.receipt.controller;

import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;


/**
 * <p> runs the receipt BOD process, which processes all approved receipts and settles their corresponding advices.</p>
 */
@Controller
@PropertySource("classpath:status.properties")
public class ReceiptBOD {

    @Autowired
    ReceiptService receiptService;

    // initialise status properties
    @Value("${status.settled}")
    private String settled;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.closed}"))
    private String closed;


    /**
     * <p> Get mapping for running the receipt BOD process. returns a modelAndView showing the number of receipts
     * processed </p>
     * @return modelAndVew showing how many receipts were processed.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value = "/receiptBOD/run")
    public ModelAndView runReceiptBOD(){
        ModelAndView mv = new ModelAndView("views/receipt/receiptSuccess");
        List<Receipt> receiptList = receiptService.getReceiptList();
        receiptList.removeIf(r -> !approved.equals(r.getReceiptStatus()));
        int processedCounter = 0;
        for(Receipt r : receiptList){
            r.setReceiptStatus(settled);

            // creating advices and settlements here because they aren't used anywhere else.
            Advice advice = new Advice();
            advice.setAmountDue(r.getReceiptAmount());
            advice.setDate(LocalDate.now());
            advice.setType(r.getReceiptPurpose());
            advice.setAdviceType(r.getReceiptType());
            advice.setStatus(closed);
            advice.setLoanApplicationNumber(r.getLoanApplicationNumber());
            advice.setInstallmentNo(1);

            Settlement settlement = new Settlement();
            settlement.setAmountDue(r.getReceiptAmount());
            settlement.setAmountPaid(r.getReceiptAmount());
            settlement.setStatus(settled);
            settlement.setAdviceId(advice);
            settlement.setReceiptNo(r);

            Boolean success = receiptService.runBOD(r, advice, settlement);
            if(success){
                // if update was successful, count as a processed receipt.
                processedCounter++;
            }
        }

        mv.addObject("messageHeader", "Receipt BOD process ran successfully");
        mv.addObject("messageBody", "Processed " + processedCounter + " of " +
                     receiptList.size() + " receipts");

        mv.addObject("receiptNumber", "N/A");

        return mv;
    }
}
