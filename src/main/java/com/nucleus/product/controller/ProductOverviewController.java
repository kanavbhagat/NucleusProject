package com.nucleus.product.controller;

import com.nucleus.product.model.Product;
import com.nucleus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/product/{productId}")
    public ModelAndView productViewById(@PathVariable(value = "productId") String productId){
        Product product = productService.getProductById(productId);
        ModelAndView modelAndView = new ModelAndView("views/product/newProductCreationChecker");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping(value = "/product/{productId}/update")
    public ModelAndView updateProductStatus(@PathVariable(value = "productId") String productId, @RequestParam("action") String action){
        ModelAndView modelAndView = new ModelAndView("views/product/newProductSave");
        System.out.println(action);
        Product product = productService.getProductById(productId);
        product.setStatus(action);
        product = productService.updateProduct(product);
        if(product==null){
            modelAndView.addObject("message", "Product was not " + action +" due to an error. Please try again.");
        }
        modelAndView.addObject("message", "Product was " + action + " successfully.");
        return modelAndView;

    }

}
