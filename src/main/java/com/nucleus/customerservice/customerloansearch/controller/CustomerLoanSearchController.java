package com.nucleus.customerservice.customerloansearch.controller;

import com.nucleus.customer.model.Customer;
import com.nucleus.customerservice.customerloansearch.service.CustomerLoanSearchService;
import com.nucleus.loanaplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerLoanSearchController {

    @Autowired
    CustomerLoanSearchService customerLoanSearchService;

    @GetMapping("/customerLoanSearch")
    public ModelAndView customerLoanSearch(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/customerServiceSearch");
        return mv;

    }

    @PostMapping("/customerLoanSearch")
    public ModelAndView customerLoanDetail(@RequestParam(value="customerCode") String customerId, @RequestParam(value="loanApplicationNumber") String loanApplicationNumber){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/searchResult");
//        Customer customer=customerLoanSearchService.getCustomerDetails(customerId);
//  //      LoanApplications loanApplications=customerLoanSearchService.getLoanApplicationDetails(Integer.parseInt(loanApplicationNumber));
//        List<Customer> customerList=new ArrayList<>();
//        if(customer!=null)
//            customerList.add(customer);
//
//        mv.addObject("customer",customerList);
//
////        if(loanApplications!=null)
////            mv.addObject("loanApplications",loanApplications);
////        System.out.println(customerId);
////        System.out.println(loanApplicationNumber);
        return mv;
    }
}




