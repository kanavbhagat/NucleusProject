package com.nucleus.receipt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class receiptCheckerController {

    @GetMapping(value = {"/receiptChecker" })
    public ModelAndView receiptChecker(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/receipt/receiptChecker");
        return mv;
    }

}
