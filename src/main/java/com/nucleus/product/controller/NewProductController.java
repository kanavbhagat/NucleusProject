package com.nucleus.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewProductController {

    @GetMapping(value = {"/newProduct" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/product/newProductCreation");
        return modelAndView;
    }
}
