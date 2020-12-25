package com.nucleus.chargepolicy.controller;


import com.nucleus.charge.dao.ChargeDao;
import com.nucleus.charge.model.NewCharge;
import com.nucleus.chargepolicy.service.ChargePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.web.servlet.ModelAndView;
//import spring.service.ChargePolicyService;

import javax.validation.Valid;
import javax.xml.bind.SchemaOutputResolver;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@PropertySource("classpath:status.properties")
@RequestMapping("chargePolicy")
public class ChargePolicyController {

    @Autowired
    ChargePolicyService chargePolicyService;

    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.saved}"))
    private String saved;

    /*@Autowired
    private ChargeDao chargeDao;*/

    public ChargePolicyService getChargePolicyService() {
        return chargePolicyService;
    }

    public void setChargePolicyService(ChargePolicyService chargePolicyService) {
        this.chargePolicyService = chargePolicyService;
    }


    @GetMapping(value = {"/newChargePolicy"})
    public String showForm(ModelMap model) {
        ChargePolicy chargePolicy = new ChargePolicy();

        // model.put("chargePolicyCode", chargePolicy.getChargePolicyCode());
        List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
        //chargePolicy.setChargeCodeName(getChargeCodeName(chargeCodeList.get(0)));
        model.put("chargePolicy", chargePolicy);
        model.put("chargeCodeList", chargeCodeList);
        return "views/chargepolicy/chargePolicy";
    }


    @PostMapping(value = {"/newChargePolicy"})
    public String getFormData(@Valid @ModelAttribute("chargePolicy") ChargePolicy chargePolicy, BindingResult bindingResult, @RequestParam("action") String action, ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("Error...........................................................");
            List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
            model.put("chargeCodeList", chargeCodeList);
            return "views/chargepolicy/chargePolicy";
        } else {
            if (action.equalsIgnoreCase("save")) {
                chargePolicy.setStatus(saved);
            } else {
                chargePolicy.setStatus(pending);
            }

            System.out.println("   *(*******  " + chargePolicy.getCharge().getChargeCode());
            chargePolicy.setCreatedDate(String.valueOf(LocalDate.now()));
            chargePolicy.setCreatedBy(getPrincipal());
            System.out.println("Inserted by " + getPrincipal());
            int status = this.chargePolicyService.insert(chargePolicy);
            if (status == 2) {
                model.put("exception", "Duplicate Charge Policy Code");
                List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();
                model.put("chargeCodeList", chargeCodeList);
                return "views/chargepolicy/chargePolicy";
            } else if (status == 1) {
                model.put("chargePolicyCode", chargePolicy.getChargePolicyCode());
                model.put("status", "saved");
                return "views/chargepolicy/SuccessPage";
            }

        }
        return "views/chargepolicy/chargePolicy";
    }

    @GetMapping(value = {"/searchScreen"})
    public String showAllEligibilityPolicies(ModelMap modelMap) {
        List<ChargePolicy> chargePolicyList = new ArrayList<ChargePolicy>();
        chargePolicyList.addAll(this.chargePolicyService.getPolicyList());
        ChargePolicy chargePolicy = new ChargePolicy();
        modelMap.put("chargePolicy", chargePolicy);
        modelMap.put("chargePolicyList", chargePolicyList);
        //odelMap.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/chargePolicySearch";
    }


    @PostMapping(value = {"/newChargePolicy/getCharge", "edit/newChargePolicy/getCharge"}, produces = "application/json")
    public @ResponseBody
    NewCharge getCharge(@RequestBody NewCharge charge) {
        // getCharge from the Model.
        // Take the chargeCode go to the db get the chargeCodeName and fill it.
        System.out.println("In getCharge Controllerrrr with  " + charge.getChargeCode());
        charge.setChargeCodeName(getChargeCodeName(charge.getChargeCode()));

        return charge;
    }


    @GetMapping(value = {"/get/{chargePolicyCode}"})
    public ModelAndView showOneChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {
        ModelAndView modelAndView = new ModelAndView();
        ChargePolicy chargePolicy = chargePolicyService.getChargePolicy(chargePolicyCode);
        System.out.println("Charge Policy Name : - " + chargePolicy.getChargePolicyName());
        modelAndView.addObject("chargePolicyForApproval", chargePolicy);
        modelAndView.addObject("chargeCodeName", getChargeCodeName(chargePolicy.getCharge().getChargeCode()));
        modelAndView.setViewName("views/chargepolicy/chargePolicyApprovalScreen");
        return modelAndView;
    }

    @GetMapping(value = {"/edit/{chargePolicyCode}"})
    public ModelAndView editChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {

        ModelAndView modelAndView = new ModelAndView();
        ChargePolicy chargePolicy = chargePolicyService.getChargePolicy(chargePolicyCode);
        System.out.println("Charge Policy Code : - " + chargePolicy.getChargePolicyCode());
        List<String> chargeCodeList = this.chargePolicyService.getChargeCodesList();

        modelAndView.addObject("chargeCodeList", chargeCodeList);
        modelAndView.addObject("chargePolicyForEdit", chargePolicy);
        modelAndView.addObject("chargePolicySelected", chargePolicy.getCharge().getChargeCode());
        // System.out.println( "Charge Code " + chargePolicy.getChargeCode());
        String chargeCodeName = this.chargePolicyService.getChargeCodeName(chargePolicy.getCharge().getChargeCode());
        System.out.println("Charge Code NAme" + chargeCodeName);
        modelAndView.addObject("chargeCodeName", chargeCodeName);
        modelAndView.setViewName("views/chargepolicy/chargePolicyEditScreen");
        return modelAndView;
    }

    @GetMapping(value = {"/delete/{chargePolicyCode}"})
    public ModelAndView deleteChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {
        ModelAndView modelAndView = new ModelAndView();
        chargePolicyService.deleteChargePolicy(chargePolicyCode);
        modelAndView.addObject("chargePolicyCode", chargePolicyCode);
        modelAndView.addObject("status", "deleted");
        modelAndView.setViewName("views/chargepolicy/SuccessPage");
        return modelAndView;
    }

    @PostMapping(value = {"/updateStatus/{chargePolicyCode}"})
    public String updateStatus(@PathVariable("chargePolicyCode") String chargePolicyCode, @RequestParam("action") String action, Model model) {
        String newStatus;
        if (action.equalsIgnoreCase("approve")) {
            newStatus = "Approved";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "Rejected";
        } else
            newStatus = "Pending";
        System.out.println(newStatus);
        String approvedBy = getPrincipal();
        this.chargePolicyService.updateStatus(chargePolicyCode, newStatus, approvedBy);
        return "redirect:/chargePolicy/searchScreen";
    }

    @PostMapping(value = {"/updateEntry/{chargePolicyCode}"})
    public ModelAndView updateEntry(@RequestParam("action")String action,@PathVariable("chargePolicyCode") String chargePolicyCode, @ModelAttribute ChargePolicy chargePolicy) {
        System.out.println("In update Entry");
        ModelAndView modelAndView = new ModelAndView();
        chargePolicy.setCreatedDate(String.valueOf(LocalDate.now()));
        chargePolicy.setStatus(this.chargePolicyService.getChargePolicy(chargePolicyCode).getStatus());
        if (action.equalsIgnoreCase("save")) {
            chargePolicy.setStatus(saved);
        } else {
            chargePolicy.setStatus(pending);
        }
        System.out.println("chargePolicy " + chargePolicy.getChargePolicyName());
        chargePolicy.setModifiedBy(getPrincipal());
        this.chargePolicyService.updateEntry(chargePolicy, chargePolicyCode);
        modelAndView.addObject("status", "edited");
        modelAndView.addObject("chargePolicyCode", chargePolicyCode);
        modelAndView.setViewName("views/chargepolicy/SuccessPage");
        return modelAndView;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private String getChargeCodeName(String chargeCode) {
        return this.chargePolicyService.getChargeCodeName(chargeCode);
    }
}
