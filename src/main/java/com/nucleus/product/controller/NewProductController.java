package com.nucleus.product.controller;


import com.nucleus.chargepolicy.service.ChargePolicyService;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import com.nucleus.product.model.Product;
import com.nucleus.product.service.ProductService;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewProductController {

    @Autowired
    ProductService productService;

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;

    @Autowired
    RepaymentPolicyService repaymentPolicyService;

    @Autowired
    ChargePolicyService chargePolicyService;

    private List<String> productTypes;

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/newProduct" })
    public ModelAndView newProduct() {
        ModelAndView modelAndView = this.addAttributes(new ModelAndView("views/product/newProductCreation"));
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = {"/newProduct", "/product/{productId}/save"})
    public ModelAndView addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                   @PathVariable(value = "productId", required = false) String id,
                                   @RequestParam("action") String action
                                   ){

        ModelAndView modelAndView;

        System.out.println(result);
        if (result.hasErrors()) {
            if(id!=null){
                return this.addAttributes( new ModelAndView("views/product/editProduct"));
            }
            modelAndView = this.addAttributes( new ModelAndView("views/product/newProductCreation"));
            return modelAndView;
        }

        EligibilityPolicy epolicy = eligibilityPolicyService.getOneEligibilityPolicy(product.getEligibilityPolicyCodeString());
        RepaymentPolicy rpolicy = repaymentPolicyService.getRepaymentPolicyById(product.getRepaymentPolicyCodeString());


        modelAndView = new ModelAndView("views/product/newProductSave");
        product.setEligibilityPolicyCode(epolicy);
        product.setRepaymentPolicyCode(rpolicy);

        if(product.getChargeCodePolicyString()!=null){
            // TODO: 20/12/20 ask Jigme team to fix single charge policy retrieval
            product.setChargeCodePolicy(null);
        }
        if("Updated".equals(action)){
            return this.updateProduct(product, modelAndView, action);
        }
        return saveNewProduct(product, modelAndView, action);
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/product/{productId}/edit")
    public ModelAndView editProduct(@PathVariable(value = "productId") String productId){
        ModelAndView modelAndView = this.addAttributes(new ModelAndView("views/product/editProduct"));
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("product1", productService.getProductById(productId));
        return modelAndView;
    }


    private ModelAndView saveNewProduct(Product product, ModelAndView modelAndView, String action){
        LoginDetailsImpl details = new LoginDetailsImpl();
        product.setCreatedBy(details.getUserName());
        product.setCreateDate(LocalDate.now());
        product.setStatus(action);
        Boolean success = productService.createNewProduct(product);

        if(success){
            modelAndView.addObject("message", "Product was " + action + " successfully." );
        }
        else{
            modelAndView.addObject("message", "Product Creation Failed");
        }
        return modelAndView;
    }


    private ModelAndView updateProduct(Product product, ModelAndView modelAndView, String action){
        LoginDetailsImpl details = new LoginDetailsImpl();
        product.setModifiedBy(details.getUserName());
        product.setModifiedDate(LocalDate.now());

        product = productService.updateProduct(product);
        if(product==null){
            modelAndView.addObject("message", "Product was not " + action + " successfully." );
        }
        else{
            modelAndView.addObject("message", "Product was " + action + " successfully." );
        }
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
        modelAndView.addObject("chargePolicies", chargePolicyService.getPolicyList());
        modelAndView.addObject("productTypes", getProductTypes());
        return modelAndView;
    }
}
