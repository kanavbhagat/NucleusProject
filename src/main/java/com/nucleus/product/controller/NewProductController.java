package com.nucleus.product.controller;

import com.nucleus.chargepolicy.model.ChargePolicy;
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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
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

        if (result.hasErrors()) {
            if(id!=null){
                return this.addAttributes( new ModelAndView("views/product/editProduct"));
            }
            modelAndView = this.addAttributes( new ModelAndView("views/product/newProductCreation"));
            return modelAndView;
        }

        EligibilityPolicy epolicy = eligibilityPolicyService.getOneEligibilityPolicy(product.getEligibilityPolicyCodeString());
        RepaymentPolicy rpolicy = repaymentPolicyService.getRepaymentPolicyById(product.getRepaymentPolicyCodeString());

        product.setEligibilityPolicyCode(epolicy);
        product.setRepaymentPolicyCode(rpolicy);
        product.setStatus(action);

        if(product.getChargeCodePolicyString()!=null){
            ChargePolicy cpolicy = chargePolicyService.getChargePolicy(product.getChargeCodePolicyString());
            product.setChargeCodePolicy(cpolicy);
        }

        if(id!=null){
            return this.updateProduct(product);
        }
        return saveNewProduct(product);
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/product/{productId}/edit")
    public ModelAndView editProduct(@PathVariable(value = "productId") String productId){
        ModelAndView modelAndView = this.addAttributes(new ModelAndView("views/product/editProduct"));
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("product1", productService.getProductById(productId));
        return modelAndView;
    }


    private ModelAndView saveNewProduct(Product product){
        LoginDetailsImpl details = new LoginDetailsImpl();
        product.setCreatedBy(details.getUserName());
        product.setCreateDate(LocalDate.now());

        Boolean success = productService.createNewProduct(product);

        if(success){
            ModelAndView modelAndView = new ModelAndView("views/product/productSuccess");
            modelAndView.addObject("messageHeader", "Product was created successfully" );
            modelAndView.addObject("messageBody", "You successfully created the product" );
            modelAndView.addObject("productCode", product.getProductCode());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("views/product/productError");
        modelAndView.addObject("messageHeader", "Product could not be created" );
        modelAndView.addObject("messageBody", "Product creation failed. Please try again" );
        modelAndView.addObject("productCode", product.getProductCode());
        return modelAndView;
    }


    private ModelAndView updateProduct(Product product){
        LoginDetailsImpl details = new LoginDetailsImpl();
        product.setModifiedBy(details.getUserName());
        product.setModifiedDate(LocalDate.now());
        String productCode = product.getProductCode();
        product = productService.updateProduct(product);
        if(product!=null){
            ModelAndView modelAndView = new ModelAndView("views/product/productSuccess");
            modelAndView.addObject("messageHeader", "Product was updated successfully" );
            modelAndView.addObject("messageBody", "You successfully updated the product" );
            modelAndView.addObject("productCode", product.getProductCode());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("views/product/productError");
        modelAndView.addObject("messageHeader", "Product could not be updated" );
        modelAndView.addObject("messageBody", "Product update failed. Please try again" );
        modelAndView.addObject("productCode", productCode);
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
