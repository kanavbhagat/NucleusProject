package com.nucleus.payment.controller;

import com.nucleus.payment.model.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewPaymentController {
    @GetMapping(value = "/newPayment")
    public ModelAndView newPayment(){
        ModelAndView modelAndView = new ModelAndView("views/payment/newPayment");
        modelAndView.addObject("payment", new Payment());
        return modelAndView;
    }

    @PostMapping(value = "/newPayment")
    public ModelAndView addPayment(){
        ModelAndView modelAndView = new ModelAndView("views/payment/checkPayment");
        return modelAndView;
    }
}
