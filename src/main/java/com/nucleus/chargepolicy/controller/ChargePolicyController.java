package com.nucleus.chargepolicy.controller;


import com.nucleus.charge.model.NewCharge;
import com.nucleus.chargepolicy.service.ChargePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("chargePolicy")
public class ChargePolicyController {

    @Autowired
    ChargePolicyService chargePolicyService;

    public ChargePolicyService getChargePolicyService() {
        return chargePolicyService;
    }

    public void setChargePolicyService(ChargePolicyService chargePolicyService) {
        this.chargePolicyService = chargePolicyService;
    }



    @GetMapping(value = {"/newChargePolicy"})
    public String showForm(ModelMap model){
        ChargePolicy chargePolicy = new ChargePolicy();
        model.put("chargePolicy",chargePolicy);
        // model.put("chargePolicyCode", chargePolicy.getChargePolicyCode());
        List<String> chargeCodeList = new ArrayList<String>();
        chargeCodeList.add("101");
        chargeCodeList.add("102");
        model.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/chargePolicy";
    }
    @GetMapping(value = {"/newChargePolicySuccess"})
    public String showFormAfterSuccess(ModelMap model){
        ChargePolicy chargePolicy = new ChargePolicy();
        model.put("chargePolicy",chargePolicy);
        // model.put("chargePolicyCode", chargePolicy.getChargePolicyCode());
        List<String> chargeCodeList = new ArrayList<String>();
        chargeCodeList.add("101");
        chargeCodeList.add("102");
        model.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/newChargePolicySuccess";
    }

    @PostMapping(value = {"/newChargePolicy"})
    public String getFormData(@Valid @ModelAttribute("chargePolicy") ChargePolicy chargePolicy,BindingResult bindingResult,@RequestParam("action")String action,ModelMap model){

        if(bindingResult.hasErrors()){
            System.out.println("Error...........................................................");
            List<String> chargeCodeList = new ArrayList<String>();
            chargeCodeList.add("101");
            chargeCodeList.add("102");
            model.put("chargeCodeList",chargeCodeList);
            return "views/chargepolicy/chargePolicy";
        }
        else{
            if(action.equalsIgnoreCase("save")) {
                chargePolicy.setStatus("Inactive");
            } else {
                chargePolicy.setStatus("Pending");
            }

            int status = this.chargePolicyService.insert(chargePolicy);
            if (status == 2){
                model.put("exception", "Duplicate Charge Policy Code");
                List<String> chargeCodeList = new ArrayList<String>();
                chargeCodeList.add("101");
                chargeCodeList.add("102");
                model.put("chargeCodeList",chargeCodeList);
                return "views/chargepolicy/chargePolicy";
            }
            else if(status == 1){
                model.put("message", "Saved Successfully");
                return "redirect:/chargePolicy/searchScreen";
            }

        }
        return "views/chargepolicy/chargePolicy";
    }
    @PostMapping(value = {"/newChargePolicySuccess"})
    public String getFormDataPostSuccess(@Valid @ModelAttribute ChargePolicy chargePolicy,BindingResult bindingResult,@RequestParam("action")String action,ModelMap model){
        System.out.println("in getForm Data");
        if(bindingResult.hasErrors()){
            System.out.println("Error...........................................................");
            List<String> chargeCodeList = new ArrayList<String>();
            chargeCodeList.add("101");
            chargeCodeList.add("102");
            model.put("chargeCodeList",chargeCodeList);
            return "views/chargepolicy/newChargePolicySuccess";
        }
        else{
            if(action.equalsIgnoreCase("save")) {
                chargePolicy.setStatus("Inactive");
            } else  {
                chargePolicy.setStatus("Pending");
            }
            this.chargePolicyService.insert(chargePolicy);
            return "redirect:/chargePolicy/newChargePolicySuccess";
        }

    }



    @GetMapping(value = {"/searchScreen"})
    public String showAllEligibilityPolicies(ModelMap modelMap) {
        List<ChargePolicy> chargePolicyList = new ArrayList<ChargePolicy>();
        chargePolicyList.addAll(this.chargePolicyService.getPolicyList());
        List<String> chargeCodeList = this.chargePolicyService.getChargeCodes();
        ChargePolicy chargePolicy = new ChargePolicy();
        modelMap.put("chargePolicy",chargePolicy);
        modelMap.put("chargePolicyList",chargePolicyList);
        //odelMap.put("chargeCodeList",chargeCodeList);
        return "views/chargepolicy/chargePolicySearch";
    }
    @GetMapping(value = {"/getCharge"})
    public void getCharge(ModelMap modelMap) {
        System.out.println("in getCharge");
       this.chargePolicyService.getCharge("1010");
    }

    @PostMapping(value = "/newChargePolicy/getCharge", produces = "application/json")
    public @ResponseBody NewCharge getCharge(@RequestBody NewCharge charge) {
        // getCharge from the Model.
        // Take the chargeCode go to the db get the chargeCodeName and fill it.
        System.out.println("In getCharge Controllerrrr");
        if(charge.getChargeCode().equals("101"))charge.setChargeCodeName("Disbursal");
        if(charge.getChargeCode().equals("102"))charge.setChargeCodeName("CGST");

        return charge;
    }

   /* @PostMapping(value = "/insertChargePolicy", produces = "text/plain")
    public @ResponseBody String insertChargePolicy(@Valid @RequestBody ChargePolicy chargePolicy, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("Errors in the Page .. .. . . . .");
            return bindingResult.toString();
        }
        System.out.println(chargePolicy.getChargePolicyName() + " charggggggg");
        this.chargePolicyService.insert(chargePolicy);
        //put condition while handling exceptionsss
        return "Successfully inserted";
    }*/


    @GetMapping(value = {"/get/{chargePolicyCode}"})
    public ModelAndView showOneChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {
        ModelAndView modelAndView = new ModelAndView();
        ChargePolicy chargePolicy = chargePolicyService.getChargePolicy(chargePolicyCode);
        System.out.println("Charge Policy Name : - " + chargePolicy.getChargePolicyName());
        modelAndView.addObject("chargePolicyForApproval", chargePolicy);
        modelAndView.setViewName("views/chargepolicy/chargePolicyApprovalScreen");
        return modelAndView;
    }
    @GetMapping(value = {"/edit/{chargePolicyCode}"})
    public ModelAndView editChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {

        ModelAndView modelAndView = new ModelAndView();
        ChargePolicy chargePolicy = chargePolicyService.getChargePolicy(chargePolicyCode);
        System.out.println("Charge Policy Name : - " + chargePolicy.getChargePolicyName());
        modelAndView.addObject("chargePolicyForEdit", chargePolicy);
        modelAndView.setViewName("views/chargepolicy/chargePolicyEditScreen");
        return modelAndView;
    }
    @GetMapping(value = {"/delete/{chargePolicyCode}"})
    public String deleteChargePolicyCode(@PathVariable("chargePolicyCode") String chargePolicyCode) {
        ModelAndView modelAndView = new ModelAndView();
        chargePolicyService.deleteChargePolicy(chargePolicyCode);

        return "redirect:/chargePolicy/searchScreen";
    }

    @PostMapping(value = {"/updateStatus/{chargePolicyCode}"})
    public String updateStatus(@PathVariable("chargePolicyCode") String chargePolicyCode, @RequestParam("action")String action, Model model) {
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "Approved";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "Rejected";
        } else
            newStatus = "Pending";
        System.out.println(newStatus);
        this.chargePolicyService.updateStatus(chargePolicyCode,newStatus);
        return "redirect:/chargePolicy/searchScreen";
    }
    @PostMapping(value = {"/updateEntry/{chargePolicyCode}"})
    public String updateEntry(@PathVariable("chargePolicyCode") String chargePolicyCode,@ModelAttribute ChargePolicy chargePolicy) {

        chargePolicy.setCreatedDate(String.valueOf(LocalDate.now()));
        chargePolicy.setStatus(this.chargePolicyService.getChargePolicy(chargePolicyCode).getStatus());
        System.out.println("chargePolicy " + chargePolicy.getChargePolicyName());
        this.chargePolicyService.updateEntry(chargePolicy,chargePolicyCode);
        return "redirect:/chargePolicy/searchScreen";
    }
}
