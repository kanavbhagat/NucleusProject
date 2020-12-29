package com.nucleus.product.controller;

import com.nucleus.product.model.Product;
import com.nucleus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductOverviewController {

    @Autowired
    ProductService productService;

    @GetMapping(value = {"/product" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/product/productOverview");
        modelAndView.addObject("products", productService.getProductList());
        return modelAndView;
    }
}
