package com.nucleus.loanapplications.controller;

import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.AddressService;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.NewLoanApplicationService;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.product.model.Product;
import com.nucleus.product.service.ProductService;

import com.nucleus.repaymentschedule.service.RepaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.nucleus.repaymentschedule.service.RepaymentScheduleServiceImpl;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewLoanApplicationController {

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @Autowired
    NewCustomerService newCustomerService;

    @Autowired
    AddressService addressService;

    @Autowired
    LoginDetailsImpl loginDetails;

    @Autowired
    ProductService productService;


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class , new DateEditor());
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/newLoanApplication")
    public ModelAndView addNewLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanapplication/loanInformation");
        modelAndView.addObject("loanApplications",new LoanApplications());
        modelAndView.addObject("productType" , getProductType());

        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/newLoanApplication")
    public ModelAndView addCustomer(@Valid @ModelAttribute LoanApplications loanApplications , HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Address address = (Address) session.getAttribute("address");



        loanApplications.setCustomerCode(customer);
        List<LoanApplications> loanApplications1 = new ArrayList<>();
        loanApplications1.add(loanApplications);
        customer.setLoanApplications(loanApplications1);
        loanApplications.setStatus("Pending");
        loanApplications.setCreateDate(LocalDate.now());
        loanApplications.setCreatedBy(loginDetails.getUserName());


        boolean a =  newCustomerService.createNewCustomer(customer);
       if(a)
           Customer.id++;

        boolean b =addressService.insertAddress(address);
        boolean c = newLoanApplicationService.addLoanApplication(loanApplications);






        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("a" ,a);
        modelAndView.addObject("b",b);
        modelAndView.addObject("c",c);
        modelAndView.setViewName("redirect:/loanApplication");

        return modelAndView;
    }
    public List<String> getProductType(){
        List<String> productType = new ArrayList<>();
        productType.add("Home Loan");
        productType.add("Education Loan");
        List<Product> products = productService.getProductList();
        for(Product product:products){
            productType.add(product.getProductName());
        }
        return productType;
    }
}
