package com.nucleus.customer.controller;

import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.customer.model.Customer;
import com.nucleus.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class NewCustomerController {

    @Autowired
    NewCustomerService newCustomerService;

    @GetMapping(value = "/newCustomer")
    public ModelAndView newCustomer(){


        ModelAndView modelAndView=new ModelAndView("views/customerInfo/testCustomer");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping(value = "/newCustomer")
    public String addCustomer(@Valid @ModelAttribute Customer customer){
         newCustomerService.createNewCustomer(customer);
         return "views/customerInfo/success";
    }
}
