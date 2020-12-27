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


/**
 * This class acts as a Controller for all
 * New Customer related operations.
 */

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


    /**
     * This method is used to display form to get details of new customers.
     *
     * @return ModelAndView This returns a view of New customer Form.
     */
    @PreAuthorize("hasRole('MAKER')")
    @GetMapping(value = "/newCustomer")
    public ModelAndView newCustomer(){


        ModelAndView modelAndView=new ModelAndView("views/customerInfo/customerInfo");

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    /**
     * This method is used to add Customer details to database.
     *
     * @param customer This is the model attribute received from the Form.
     *
     * @param request This model is used to create https sessions to temporarily store form data.
     *
     * @return ModelAndView This redirects to a view of form of New Loan Application.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/newCustomer")
    public ModelAndView addCustomer(@Valid @ModelAttribute Customer customer ,  HttpServletRequest request){


        Address add = customer.getAdd();
        add.setCustomerCode(customer);
        List<Address> addresses = new ArrayList<>();
        addresses.add(add);
        customer.setAddresses(addresses);

        HttpSession session = request.getSession();
        session.setAttribute("customer" , customer);
        session.setAttribute("address" ,add);


        return new ModelAndView("redirect:/newLoanApplication");
    }

}
