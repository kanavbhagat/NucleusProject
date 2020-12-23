package com.nucleus.customer.controller;





import com.nucleus.customer.model.Address;
import com.nucleus.customer.service.AddressService;

import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.customer.model.Customer;
import com.nucleus.payment.service.DateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewCustomerController {

    @Autowired
    NewCustomerService newCustomerService;

    @Autowired
    AddressService addressService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class , new DateEditor());
    }


    @PreAuthorize("hasRole('MAKER')")
    @GetMapping(value = "/newCustomer")
    public ModelAndView newCustomer(){


        ModelAndView modelAndView=new ModelAndView("views/customerInfo/customerInfo");
/*
        ModelAndView modelAndView=new ModelAndView("views/customerInfo/testCustomer");*/

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/newCustomer")
    public ModelAndView addCustomer(@Valid @ModelAttribute Customer customer ,  HttpServletRequest request){
       // customer.setCustomerCode("L102");


        Address add = customer.getAdd();
        add.setCustomerCode(customer);
        List<Address> addresses = new ArrayList<>();
        addresses.add(add);
        customer.setAddresses(addresses);

        HttpSession session = request.getSession();
        session.setAttribute("customer" , customer);
        session.setAttribute("address" ,add);

        /*boolean customerAdded  = newCustomerService.createNewCustomer(customer);
        boolean addressAdded = addressService.insertAddress(add);*/

       //ModelAndView modelAndView=new ModelAndView("views/customerInfo/success");
       /*modelAndView.addObject("addAddress" , addressAdded);
       modelAndView.addObject("addCust" , customerAdded);*/
       // modelAndView.addObject("address", add);


        return new ModelAndView("redirect:/newLoanApplication");
    }

}
