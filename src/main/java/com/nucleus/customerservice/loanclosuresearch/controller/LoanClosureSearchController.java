package com.nucleus.customerservice.loanclosuresearch.controller;

import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * The LoanClosureSearchController class searches the closed Loans
 * by calling the required method of the New Loan Application Service Class with the
 * help of an object of LoanService Interface. It also defines the getter
 * and setter methods for the mentioned object.
 */

@Controller
public class LoanClosureSearchController {
    @Autowired
    NewCustomerService customerService;

    @Autowired
    LoanApplicationService loanApplicationService;


    /**
     * controller for LoanClosureForm View
     * @return view of LoanClosureForm
     */
    @GetMapping(value = {"/loanClosureForm" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/customerservice/loanClosureSearch/LoanClosureForm");
        return modelAndView;
    }

    /**
     * controller for LoanClosureDetails View
     * @return ModelAndView of loan Details and customers
     */


    @PostMapping(value = "/customerLoanClosure")
    public @ResponseBody
    ModelAndView getLoanClosures(@RequestParam(value="customerCode", required = false) String customerId,
                                    @RequestParam(value="loanApplicationNumber", required = false) Integer loanApplicationNumber){

        ModelAndView mv = new ModelAndView("views/customerservice/loanClosureSearch/searchResult");
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

            /*
            * Uncomment the below line to get closed loans
            * */

            //loanApplications.removeIf(la -> !la.getStatus().equalsIgnoreCase("closed"));

            mv.addObject("customer", customers);
            mv.addObject("loanApplications", loanApplications);
        }
        else{
            // if both customer code and loan application number are present.
            mv.setViewName("views/customerservice/loanClosureSearch/searchError");
            mv.addObject("messageHeader", "No results found");
            mv.addObject("messageBody", "No results were found matching your criteria. Please try again");
        }
        return mv;
    }

}


