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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.security.access.prepost.PreAuthorize;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The NewLoanApplicationController class creates a new loan application
 * by calling the required method of the New Loan Application Service Class with the
 * help of an object of NewLoanApplicationService Class. It also defines the getter
 * and setter methods for the mentioned object.
 */

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

    /**
     * This is the main method which is authorized and then is triggered. It calls the
     * newLoanApplication method of Service class with the help of its object
     * declared above.
     *
     * @return modelAndView This returns a view of loanInformation form.
     */

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/newLoanApplication")
    public ModelAndView addNewLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanapplication/loanInformation");
        modelAndView.addObject("loanApplications",new LoanApplications());
        modelAndView.addObject("productType" , getProductType());

        return modelAndView;
    }

    /**
     * This method is used to add a Loan application (entered by the user) to the database.
     *
     * @param bindingResult This contains data validation errors, if any.
     *
     * @param loanApplications This is the modelAttribute received from the form.
     *
     * @return ModelAndView This returns a view with a Success or Error page.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/newLoanApplication")
    public ModelAndView addCustomer(@Valid @ModelAttribute LoanApplications loanApplications , BindingResult bindingResult, HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Address address = (Address) session.getAttribute("address");

        if(bindingResult.hasErrors()){

            return new ModelAndView("views/loanapplication/loanInformation").addObject("productType" , getProductType());
        }

        loanApplications.setCustomerCode(customer);
        List<LoanApplications> loanApplications1 = new ArrayList<>();
        loanApplications1.add(loanApplications);
        customer.setLoanApplications(loanApplications1);
        loanApplications.setStatus("PENDING");
        loanApplications.setCreateDate(LocalDate.now());
        loanApplications.setCreatedBy(loginDetails.getUserName());

        String productType = loanApplications.getProductType();
        Product product = getProduct(productType);


        loanApplications.setProductCode(product);


        boolean a =  newCustomerService.createNewCustomer(customer);
       if(a)
           Customer.id++;

        boolean b =addressService.insertAddress(address);
        boolean c = newLoanApplicationService.addLoanApplication(loanApplications);

        ModelAndView modelAndView = new ModelAndView();
        if(c) {
            modelAndView.addObject("customerCode", customer.getCustomerCode());
            modelAndView.addObject("loanApplicationId", loanApplications.getLoanApplicationNumber());

            modelAndView.setViewName("views/loanapplication/addedpage");
        }
        else{
            modelAndView.setViewName("views/loanapplication/RPAddErrorPage");
        }
        return modelAndView;
    }

    //Method to get product a perticular Product Type
    public Product getProduct(String productType){
        List<Product> products = productService.getProductList();
        Product res = null;
        for(Product product:products){
            if(product.getProductName().equals(productType))
                res = product;
        }
        return res;
    }

    //Method to get a list of product types
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





    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/oldCustomerLoanApplication")
    public ModelAndView addOldLoanApplication(){
        ModelAndView modelAndView= new ModelAndView("views/loanapplication/loanInformation");
        modelAndView.addObject("loanApplications",new LoanApplications());
        modelAndView.addObject("productType" , getProductType());
        return modelAndView;
    }


    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/oldCustomerLoanApplication")
    public ModelAndView addOldCustomer(@Valid @ModelAttribute LoanApplications loanApplications,HttpServletRequest request){
        HttpSession session = request.getSession();
        String customerId = (String) session.getAttribute("customerId");
//        loanApplications.setCustomerCode(newCustomerService.getCustomer(customerId));
//        List<LoanApplications> loanApplications1 = new ArrayList<>();
//        loanApplications1.add(loanApplications);
//        customer.setLoanApplications(loanApplications1);
//        loanApplications.setStatus("Pending");
//        loanApplications.setCreateDate(LocalDate.now());
//        loanApplications.setCreatedBy(loginDetails.getUserName());
//
//        String productType = loanApplications.getProductType();
//        Product product = getProduct(productType);
//        loanApplications.setProductCode(product);
//
//        boolean c = newLoanApplicationService.addLoanApplication(loanApplications);

        ModelAndView modelAndView = new ModelAndView();
        //if(customerId!=null || !customerId.isEmpty()) {
            //modelAndView.addObject("customer", customerId);
        System.out.println("//////////////////////////////////"+customerId);
//            modelAndView.addObject("loanApplicationId", loanApplications.getLoanApplicationNumber());

            modelAndView.setViewName("views/loanapplication/addedpage");
       // }
//        else{
//            modelAndView.setViewName("views/loanapplication/RPAddErrorPage");
//        }
        return modelAndView;
    }

}























