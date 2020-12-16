package com.nucleus.product.controller;

import com.nucleus.product.model.Product;
import com.nucleus.product.service.NewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class NewProductController {

    @Autowired
    NewProductService newProductService;

    @GetMapping(value = {"/newProduct" })
    public ModelAndView newProduct() {
        ModelAndView modelAndView = new ModelAndView("views/product/newProductCreation");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping(value = "/newProduct")
    public ModelAndView addProdut(@Valid Product product, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("views/product/productOverview");
        return modelAndView;
    }
}
