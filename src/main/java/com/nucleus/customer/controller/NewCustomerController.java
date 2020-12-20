package com.nucleus.customer.controller;


//temporary file -- testing purpose

import com.nucleus.customer.model.Address;
import com.nucleus.customer.service.AddressService;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class NewCustomerController {

    @Autowired
    NewCustomerService newCustomerService;

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/newCustomer")
    public ModelAndView newCustomer(){

        ModelAndView modelAndView=new ModelAndView("views/customerInfo/customerInfo");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping(value = "/newCustomer")
    public ModelAndView addCustomer(@Valid @ModelAttribute Customer customer){

        newCustomerService.createNewCustomer(customer);
       ModelAndView modelAndView=new ModelAndView("views/customerInfo/success");

       // modelAndView.addObject("customerCode" , customer);
        //modelAndView.addObject("address", new Address());
        return modelAndView;
    }
    @PostMapping(value = "/newAddress")
    public ModelAndView addAddress(@Valid @ModelAttribute Address address){
        Customer customer = newCustomerService.getCustomer("L101");
        address.setCustomerCode(customer);
       addressService.insertAddress(address);
        ModelAndView modelAndView=new ModelAndView("views/customerInfo/success");

        return modelAndView;
    }
}
