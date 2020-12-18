package com.nucleus.customer.controller;

import com.nucleus.customer.service.NewCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomerOverviewController {
    @Autowired
    NewCustomerService newCustomerService;

    @GetMapping(value = {"/customersView" })
    public ModelAndView customerOverview() {
        ModelAndView modelAndView = new ModelAndView("views/customer/customerOverview");
        modelAndView.addObject("customers", newCustomerService.getCustomerList());
        return modelAndView;
    }
}
