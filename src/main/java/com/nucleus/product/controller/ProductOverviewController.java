package com.nucleus.product.controller;

import com.nucleus.login.logindetails.LoginDetailsImpl;
import com.nucleus.product.model.Product;
import com.nucleus.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;


/**
 * <p> Controller for the product overview page and associated checker/maker actions. </p>
 */
@Controller
public class ProductOverviewController {

    @Autowired
    ProductService productService;


    /**
     * <p> Get Mapping for the product overview page. Sends a list of created products with the modelAndView for the
     * datatable.
     * </p>
     * @return the modelAndView with the new product overview view.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/product" })
    public ModelAndView productOverview() {
        ModelAndView modelAndView = new ModelAndView("views/product/productOverview");
        modelAndView.addObject("products", productService.getProductList());
        return modelAndView;
    }


    /**
     * <p> Get Mapping for the product approval page. Retrieves the product by its Id and attaches it to the returned model.
     * </p>
     * @param String productId id of the product that needs to be approved
     * @return the modelAndView with the product approval page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = "/product/{productId}")
    public ModelAndView productViewById(@PathVariable(value = "productId") String productId){
        Product product = productService.getProductById(productId);
        ModelAndView modelAndView = new ModelAndView("views/product/newProductCreationChecker");
        modelAndView.addObject("product", product);
        return modelAndView;
    }


    /**
     * <p>Post mapping for the product approval/rejection page. Receives the productId as a path variable to retrieve
     * the product from the dao. Receives the action and updates the product's status depending on the action performed.
     * </p>
     * @return the modelAndView with the success or error page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @PostMapping(value = "/product/{productId}/update")
    public ModelAndView updateProductStatus(@PathVariable(value = "productId") String productId, @RequestParam("action") String action){
        Product product = productService.getProductById(productId);
        LoginDetailsImpl details = new LoginDetailsImpl();

        product.setAuthorizedBy(details.getUserName());
        product.setAuthorizedDate(LocalDate.now());
        product.setStatus(action);

        product = productService.updateProduct(product);
        if(product!=null){
            ModelAndView modelAndView = new ModelAndView("views/product/productSuccess");
            modelAndView.addObject("messageHeader", "Product was successfully " + action );
            modelAndView.addObject("messageBody", "You successfully changed the product status." );
            modelAndView.addObject("productCode", productId);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("views/product/productError");
        modelAndView.addObject("messageHeader", "Product status could not be updated" );
        modelAndView.addObject("messageBody", "Product status update failed. Please try again" );
        modelAndView.addObject("productCode", productId);
        return modelAndView;

    }


    /**
     * <p> Get Mapping for delete product link. Returns either the success or error page depending on whether the product
     * with the corresponding productId can be deleted.
     * </p>
     * @return the modelAndView with either the success or error page.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/product/{productId}/delete")
    public ModelAndView deleteProduct(@PathVariable(value = "productId") String productId){
        Boolean success = productService.deleteProduct(productId);
        if(success){
            ModelAndView modelAndView = new ModelAndView("views/product/productSuccess");
            modelAndView.addObject("messageHeader", "Product was deleted" );
            modelAndView.addObject("messageBody", "You successfully deleted a product" );
            modelAndView.addObject("productCode", productId);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("views/product/productError");
        modelAndView.addObject("messageHeader", "Product could not be deleted" );
        modelAndView.addObject("messageBody", "Product deletion failed. Please try again" );
        modelAndView.addObject("productCode", productId);
        return modelAndView;
    }
}
