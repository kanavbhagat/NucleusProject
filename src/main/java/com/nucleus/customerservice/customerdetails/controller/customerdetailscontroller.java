package com.nucleus.customerservice.customerdetails.controller;

import com.nucleus.customer.model.Customer;
import com.nucleus.customerservice.customerdetails.service.customerdetailsservice;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * customerdetailscontroller class acts as a Controller layer for all RepaymentPolicy related operations.
 *
 * @author  Siddhant
 * @version 1.0
 * @since   2020-12-25
 */
@Controller
public class customerdetailscontroller {

        @Autowired
        private customerdetailsservice customerdetailsservice;

    /**
     * Handles get request for customer search
     * @return view containing customer details
     */
    @GetMapping(value = {"/customerdetailsform" })
        public ModelAndView getCustomerView() {
            ModelAndView modelAndView = new ModelAndView("views/customerservice/customerdetails/customerdetailsform");
            return modelAndView;
        }

    /**
     * Returns view containing the customer Details
     * @param customerCode customer code which is searched
     * @return view containing the customer Details
     */
    @GetMapping(path = "/customerdetails")
    public ModelAndView getCustomerDetailView(@RequestParam("customerCode") String customerCode){

        Customer customer=customerdetailsservice.getCustomerDetails(customerCode);

        if(customer==null){
            return new ModelAndView("views/customerservice/customerdetails/ErrorPage",
                    "Message", "Customer NOT found: "+ customerCode);
        }
        Hibernate.initialize(customer.getAddresses());
        return new ModelAndView("views/customerservice/customerdetails/customerdetailsview",
                "customerObj", customer);
    }
}




