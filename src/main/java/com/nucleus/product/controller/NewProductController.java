package com.nucleus.product.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import com.nucleus.product.model.Product;
import com.nucleus.product.service.NewProductService;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
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

    @GetMapping(value = {"/newProduct" })
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView("views/product/newProductCreation");
        List<EligibilityPolicy> eligibilityPolicies = eligibilityPolicyService.getAllEligibilityPolicies();
        modelAndView.addObject("eligibilityPolicies", eligibilityPolicies);
        modelAndView.addObject("product", new Product());
//        modelAndView.addObject("productTypes", productTypes);
//        modelAndView.addObject("repaymentPolicies", repaymentPolicies);
        return modelAndView;
    }

    @PostMapping(value = "/newProduct")
    public ModelAndView addProduct(@Valid Product product, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("views/product/productOverview");
        System.out.println(product.getEligibilityPolicyCode());
        return modelAndView;
    }
}
