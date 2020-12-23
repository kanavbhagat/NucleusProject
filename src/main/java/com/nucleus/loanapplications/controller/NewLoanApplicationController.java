package com.nucleus.loanapplications.controller;

import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.AddressService;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.NewLoanApplicationService;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.product.model.Product;
import com.nucleus.repaymentschedule.service.RepaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
public class NewLoanApplicationController {

    @Autowired
    RepaymentScheduleService rs;

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    @Autowired
    NewCustomerService newCustomerService;

    @Autowired
    AddressService addressService;


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class , new DateEditor());
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/newLoanApplication")
    public ModelAndView addNewLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanapplication/loanInformation");
        modelAndView.addObject("loanApplications",new LoanApplications());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/newLoanApplication")
    public ModelAndView addCustomer(@Valid @ModelAttribute LoanApplications loanApplications , HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Address address = (Address) session.getAttribute("address");
        Product product = new Product();
        product.setProductCode("P101");
        product.setProductName("homeLoan");
        product.setProductType("property");

        loanApplications.setCustomerCode(customer);
        List<LoanApplications> loanApplications1 = new ArrayList<>();
        loanApplications1.add(loanApplications);
        customer.setLoanApplications(loanApplications1);
        loanApplications.setStatus("Pending");

     /*   loanApplications.setProductCode(product);*/

       boolean a =  newCustomerService.createNewCustomer(customer);
        boolean b =addressService.insertAddress(address);
        boolean c = newLoanApplicationService.addLoanApplication(loanApplications);
        rs.addRepaymentSchedule(loanApplications);



        ModelAndView modelAndView = new ModelAndView("views/customerInfo/success");

        modelAndView.addObject("a" ,a);
        modelAndView.addObject("b",b);
        modelAndView.addObject("c",c);

        return modelAndView;
    }
}
