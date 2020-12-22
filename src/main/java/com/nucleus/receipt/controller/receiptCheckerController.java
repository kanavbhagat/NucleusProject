package com.nucleus.receipt.controller;

import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class receiptCheckerController {


    @Autowired
    ReceiptService receiptService;

    @GetMapping(value = {"/receiptChecker" })
    public ModelAndView receiptChecker(){
        ModelAndView mv = new ModelAndView("views/receipt/receiptChecker");
        mv.addObject("receipts", receiptService.getReceiptList());
        return mv;
    }

    @GetMapping(value = "/receiptChecker/approve/{receiptId}")
    public ModelAndView receiptCheckerApprove(@PathVariable("receiptId") String receiptIdString){
        Integer receiptId = Integer.parseInt(receiptIdString);
        ModelAndView mv = new ModelAndView("views/receipt/receiptSuccess");
        Receipt receipt = receiptService.getReceipt(receiptId);
        receipt.setReceiptStatus("Approved");
        Boolean success = receiptService.updateReceipt(receipt);
        if(success){
            mv.addObject("message", "Receipt was successfully approved");
            return mv;
        }
        mv.addObject("message", "Receipt approval failed");
        return mv;
    }

    @GetMapping(value = "/receiptChecker/reject/{receiptId}")
    public ModelAndView receiptCheckerReject(@PathVariable("receiptId") String receiptIdString){
        Integer receiptId = Integer.parseInt(receiptIdString);
        ModelAndView mv = new ModelAndView("views/receipt/receiptSuccess");
        Receipt receipt = receiptService.getReceipt(receiptId);
        receipt.setReceiptStatus("Rejected");
        Boolean success = receiptService.updateReceipt(receipt);
        if(success){
            mv.addObject("message", "Receipt was successfully rejected");
            return mv;
        }
        mv.addObject("message", "Receipt rejection failed");
        return mv;
    }


}
