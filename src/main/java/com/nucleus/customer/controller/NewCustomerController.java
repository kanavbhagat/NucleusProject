package com.nucleus.customer.controller;


import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewCustomerController {

    @Autowired
    NewCustomerService newCustomerService;

    @GetMapping(value = "/newCustomer")
    public ModelAndView newCustomer(){
        ModelAndView modelAndView=new ModelAndView("views/customer/newCustomer");
        return modelAndView;
    }

    @PostMapping(value = "/newCustomer")
    public boolean addCustomer(Customer customer, BindingResult result){
        return newCustomerService.createNewCustomer(customer);
    }
}
