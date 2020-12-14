package com.nucleus.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductOverviewController {

    @GetMapping(value = {"/product" })
    public ModelAndView productOverview() {
        return new ModelAndView("views/product/productOverview");
    }
}
