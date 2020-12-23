package com.nucleus.customerservice.customerloansearch.controller;

import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * <p> Controller handling the serving of loan summary seach, and the customerservice home.  </p>
 */
@Controller
public class CustomerLoanSearchController {

    @Autowired
    NewCustomerService customerService;

    @Autowired
    LoanApplicationService loanApplicationService;


    /**
     * <p> Get mapping for the customer loan service loan summary page.  </p>
     * @return returns a modelAndView of the customerservice search.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping("/customerLoanSearch")
    public ModelAndView customerLoanSearch(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/customerServiceSearch");
        return mv;

    }


    /**
     * <p> Get mapping for the customer service home page. </p>
     * @return returns a modelAndView of the customerservice home page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping("/customerServiceHome")
    public ModelAndView getCustomerServiceHome(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("views/customerservice/customerServiceHome");
        return mv;

    }


    /**
     * <p> Post mapping for the loan summary search. </p>
     * @param String customerId customer id to search via
     * @param Integer loanApplicationNumber loan application number to search for.
     * @return returns a modelAndView of the search results, or an error page if no results found.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @PostMapping(value = "/customerLoanSearch")
    public @ResponseBody ModelAndView customerLoanDetail(@RequestParam(value="customerCode", required = false) String customerId,
                                                         @RequestParam(value="loanApplicationNumber", required = false) Integer loanApplicationNumber){

        ModelAndView mv = new ModelAndView("views/customerservice/searchResult");
        List<Customer> customers = new ArrayList<>();
        List<LoanApplications> loanApplications = new ArrayList<>();

        if((customerId==null || customerId.isEmpty()) && loanApplicationNumber==null){
            // avoid sending nothing to model and view. This case *should* never happen.
            mv.addObject("customer", customers);
            mv.addObject("loanApplications", loanApplications);
        }

        else if(customerId==null || customerId.isEmpty()){
            // get loan application details and corresponding customer details.
            LoanApplications loanApplication = loanApplicationService.getLoanApplicationId(loanApplicationNumber);
            if(loanApplication!=null){
                customers.add(loanApplication.getCustomerCode());
                loanApplications.add(loanApplication);
            }
            System.out.println(customers.size());
            mv.addObject("customer", customers);
            mv.addObject("loanApplications", loanApplications);
        }

        else if(loanApplicationNumber==null){
            // get customer details and all loans corresponding to customer.
            Customer customer = customerService.getCustomer(customerId);
            if(customer!=null){
                customers.add(customer);
            }
            loanApplications = loanApplicationService.getAllLoanApplicationsList();
            loanApplications.removeIf(la -> !customerId.equals(la.getCustomerCode().getCustomerCode()));
            mv.addObject("customer", customers);
            mv.addObject("loanApplications", loanApplications);
        }
        else{
            // if both customer code and loan application number are present.
            LoanApplications la = loanApplicationService.getLoanApplicationId(loanApplicationNumber);
            Customer customer = customerService.getCustomer(customerId);
            if(customer!=null){
                customers.add(customer);
            }
            if(la!=null && customerId.equals(la.getCustomerCode().getCustomerCode())){
                loanApplications.add(la);
            }
            mv.addObject("customer", customers);
            mv.addObject("loanApplications", loanApplications);
        }

        if(customers.isEmpty() || loanApplications.isEmpty()){
            mv.setViewName("views/customerservice/searchError");
            mv.addObject("messageHeader", "No results found");
            mv.addObject("messageBody", "No results were found matching your criteria. Please try again");
        }

        return mv;
    }
}




