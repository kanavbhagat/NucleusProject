package com.nucleus.loanclosurebod.controller;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.service.LoanClosureService;
import com.nucleus.customer.service.NewCustomerService;
import com.nucleus.loanapplications.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * The LoanClosureController class starts the Loan Closure BOD process
 * by calling the required method of the Loan Closure Service Class with the
 * help of an object of LoanClosureService Interface. It also defines the getter
 * and setter methods for the mentioned object.
 */

@Controller
@RequestMapping("/main")
public class LoanClosureController {
    @Autowired
    NewCustomerService customerService;

    @Autowired
    LoanClosureService loanClosureService;

    @Autowired
    LoanApplicationService loanApplicationService;

    public LoanClosureService getLoanClosureService(){
        return loanClosureService;
    }
    public void setLoanClosureService(LoanClosureService loanClosureService){
        this.loanClosureService = loanClosureService;
    }

    /**
     * loanClosureBod is the main method which is triggered. It calls the
     * loaClosureBod method of Service class with the help of its object
     * declared above.
     */
    @RequestMapping("/loanClosureBod")
    public ModelAndView loanClosureBod(){
        int closedLoans = loanClosureService.loanClosureBod();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ClosedLoans", closedLoans);
        modelAndView.setViewName("views/loanclosurebod/viewClosedLoanCount");
        return modelAndView;
    }



    /**
     * controller for LoanClosureForm View
     * @return view of LoanClosureForm
     */
    @GetMapping(value = {"/loanClosureForm" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/loanclosurebod/loanClosureSearch/LoanClosureForm");
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

        ModelAndView mv = new ModelAndView("views/loanclosurebod/loanClosureSearch/searchResult");
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
            mv.setViewName("views/loanclosurebod/loanClosureSearch/searchError");
            mv.addObject("messageHeader", "No results found");
            mv.addObject("messageBody", "No results were found matching your criteria. Please try again");
        }
        return mv;
    }

}