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


/**
 * <p>Controller for the new product creation and product edit pages.</p>
 */
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

    /**
     * <p>Get Mapping for the product creation page. Sends a new product model for the new product form.
     * </p>
     * @return the modelAndView with the new product view.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/newProduct" })
    public ModelAndView newProduct() {
        ModelAndView modelAndView = this.addAttributes(new ModelAndView("views/product/newProductCreation"));
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }


    /**
     * <p> Post mapping for editing or adding a new product. Calls the appropriate createNewProduct or updateProduct
     * function depending on the action performed.
     * </p>
     * @param Product product the product to be saved/updated.
     * @param BindingResult result Binding result from the page for validation
     * @param String id productId of the product to be updated. Is optional, only present during product update.
     * @param String action the action performed, can be either Saved or Pending.
     * @return the modelAndView of either the update success or creation success page.
     */
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


    /**
     * <p> Get Mapping for the edit product screen. Retrieves the product via productId using the DAO, and then attaches
     * that object to the returned modelAndView.
     * </p>
     * @param String productId the productId who's details are to be shown.
     * @return the modelAndView of the edit page.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/product/{productId}/edit")
    public ModelAndView editProduct(@PathVariable(value = "productId") String productId){
        ModelAndView modelAndView = this.addAttributes(new ModelAndView("views/product/editProduct"));
        modelAndView.addObject("product", productService.getProductById(productId));
        return modelAndView;
    }


    /**
     * <p> Internal function called from the new product/product update controller in case the product is to be created.
     * </p>
     * @param Product product which has to be saved
     * @return the modelAndView of either the success or error page depending on whether the dao was able to insert it.
     */
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


    /**
     * <p> Internal function called from the new product/product update controller in case the product is to be updated.
     * </p>
     * @param String productId the productId who's details are to be shown.
     * @return the modelAndView of either the success or error page depending on whether the dao was able to udpate it.
     */
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


    /**
     * <p> Internal function to get the possible loan product types.
     * </p>
     * @return List of Strings containing the prduct types.
     */
    private List<String> getProductTypes(){
        List<String> productTypes = new ArrayList<>(2);
        productTypes.add("Home Loan");
        productTypes.add("Consumer Vehicle Loan");
        return productTypes;
    }


    /**
     * <p> Internal function to add all necessary attributes for the product creation or edit product screen dropdowns.
     * </p>
     * @param ModelAndView modelAndView which needs the params to be attached
     * @return modelAndView with the attributes attached.
     */
    private ModelAndView addAttributes(ModelAndView modelAndView){
        List<EligibilityPolicy> epolicies = eligibilityPolicyService.getAllEligibilityPolicies();
        List<RepaymentPolicy> rpolicies = repaymentPolicyService.getRepaymentPolicyList();
        List<ChargePolicy> cpolicies = chargePolicyService.getPolicyList();
        epolicies.removeIf(ep -> !"Approved".equals(ep.getStatus()));
        rpolicies.removeIf(rp -> !"Approved".equals(rp.getStatus()));
        cpolicies.removeIf(cp -> !"Approved".equals(cp.getStatus()));

        modelAndView.addObject("eligibilityPolicies", epolicies);
        modelAndView.addObject("repaymentPolicies", rpolicies);
        modelAndView.addObject("chargePolicies", cpolicies);
        modelAndView.addObject("productTypes", getProductTypes());
        return modelAndView;
    }
}
