package com.nucleus.product.controller;

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

    @GetMapping(value = {"/newProduct" })
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView("views/product/newProductCreation");
//        List<String> productTypes = new ArrayList<>();
//        productTypes.add("Hello");
//        List<RepaymentPolicy> repaymentPolicies = new ArrayList<>();
//        RepaymentPolicy policy1 = new RepaymentPolicy();
//        policy1.setPolicyCode("101");
//        policy1.setPolicyDescription("it do thing");
//        repaymentPolicies.add(policy1);
//
//        policy1 = new RepaymentPolicy();
//        policy1.setPolicyCode("102");
//        policy1.setPolicyDescription("it do other thing");
//        repaymentPolicies.add(policy1);

        modelAndView.addObject("product", new Product());
//        modelAndView.addObject("productTypes", productTypes);
//        modelAndView.addObject("repaymentPolicies", repaymentPolicies);
        return modelAndView;
    }

    @PostMapping(value = "/newProduct")
    public ModelAndView addProdut(@Valid Product product, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("views/product/productOverview");
        return modelAndView;
    }
}
