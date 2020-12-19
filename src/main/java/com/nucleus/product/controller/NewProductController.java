package com.nucleus.product.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import com.nucleus.product.model.Product;
import com.nucleus.product.service.NewProductService;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewProductController {

    @Autowired
    NewProductService newProductService;

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;

    @Autowired
    RepaymentPolicyService repaymentPolicyService;

    private List<String> productTypes;

    public NewProductController(){
        super();
        productTypes = getProductTypes();
    }

    @GetMapping(value = {"/newProduct" })
    public ModelAndView newProduct() {
        ModelAndView modelAndView = this.addAttributes(new ModelAndView("views/product/newProductCreation"));
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping(value = "/newProduct")
    public ModelAndView addProduct(@Valid Product product, BindingResult result){
        ModelAndView modelAndView;
        if (result.hasErrors()) {
            modelAndView = this.addAttributes( new ModelAndView("views/product/newProductCreation"));
            return modelAndView;
        }
        modelAndView = new ModelAndView("views/product/productOverview");
        // TODO: 17/12/20 get policies via policy code 
        System.out.println(product.getEligibilityPolicyCodeString());
        return modelAndView;
    }

    private List<String> getProductTypes(){
        List<String> productTypes = new ArrayList<>(2);
        productTypes.add("Home Loan");
        productTypes.add("Consumer Vehicle Loan");
        return productTypes;
    }

    private ModelAndView addAttributes(ModelAndView modelAndView){
        modelAndView.addObject("eligibilityPolicies", eligibilityPolicyService.getAllEligibilityPolicies());
        modelAndView.addObject("repaymentPolicies", repaymentPolicyService.getRepaymentPolicyList());
        modelAndView.addObject("productTypes", getProductTypes());
        return modelAndView;
    }
}
