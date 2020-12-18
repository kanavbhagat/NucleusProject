package com.nucleus.payment.controller;

import com.nucleus.payment.model.Payment;
import com.nucleus.payment.service.PaymentServiceImpl;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.payment.validator.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping(value = "payment")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    PaymentValidator paymentValidator;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.setValidator(paymentValidator);
        dataBinder.registerCustomEditor(LocalDate.class, new DateEditor());
    }

    public PaymentServiceImpl getPaymentService(){
        return paymentService;
    }

    public void setPaymentService(PaymentServiceImpl paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping(value = {"/", ""})
    public ModelAndView viewPayments(){
        ModelAndView modelAndView = new ModelAndView("views/payment/checkPayment");
        return modelAndView;
    }

    @GetMapping(value = "/newPayment")
    public ModelAndView newPayment(){
        ModelAndView modelAndView = new ModelAndView("views/payment/newPayment");
        modelAndView.addObject("payment", new Payment());
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public String addPayment(@Valid @ModelAttribute("payment") Payment payment, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return "newPayment";
        }
        boolean insertStatus = paymentService.insertPayment(payment);
        model.addAttribute("insertStatus", insertStatus);
        return "redirect:/payment";
    }

}
