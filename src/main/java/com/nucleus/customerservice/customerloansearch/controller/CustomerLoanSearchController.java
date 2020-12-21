package com.nucleus.customerservice.customerloansearch.controller;

import com.nucleus.customer.dao.CustomerDAO;
import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerLoanSearchController {

    // TODO: 17/12/20 change this later to map with customer dao services.
//    @Autowired
//    NewCustomerService customerLoanSearchService;

    @Autowired
    CustomerDAO customerDAO;

    @GetMapping("/customerLoanSearch")
    public ModelAndView customerLoanSearch(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/customerServiceSearch");
        return mv;

    }

    @GetMapping("/customerServiceHome")
    public ModelAndView getCustomerServiceHome(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/customerServiceHome");
        return mv;

    }

    @PostMapping(value = "/customerLoanSearch")
    public @ResponseBody ModelAndView customerLoanDetail(@RequestParam(value="customerCode", required = false) String customerId,
                                           @RequestParam(value="loanApplicationNumber", required = false) String loanApplicationNumber){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/searchResult");
        Customer customer=customerDAO.getCustomerById(customerId);
  //      LoanApplications loanApplications=customerLoanSearchService.getLoanApplicationDetails(Integer.parseInt(loanApplicationNumber));
        List<Customer> customerList=new ArrayList<>();
        List<LoanApplications> loanApplicationsList=new ArrayList<>();
        if(customer!=null) {
            customerList.add(customer);
            loanApplicationsList=customerDAO.getCustomerLoanDetails(customerId);
        }
        mv.addObject("customer",customerList);
        mv.addObject("loanApplications",loanApplicationsList);

////        if(loanApplications!=null)
////            mv.addObject("loanApplications",loanApplications);
////        System.out.println(customerId);
////        System.out.println(loanApplicationNumber);
        return mv;
    }
}




