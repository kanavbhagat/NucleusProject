package com.nucleus.eligibilitypolicy.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("eligibilityPolicy")
public class EligibilityPolicyController {

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;

    public EligibilityPolicyService getEligibilityPolicyService() {
        return eligibilityPolicyService;
    }

    public void setEligibilityPolicyService(EligibilityPolicyService eligibilityPolicyService) {
        this.eligibilityPolicyService = eligibilityPolicyService;
    }

    @GetMapping(value = {"/", ""})
    public ModelAndView showAllEligibilityPolicies() {
        ModelAndView modelAndView = new ModelAndView();
        List<EligibilityPolicy> eligibilityPolicyList = eligibilityPolicyService.getAllEligibilityPolicies();
        modelAndView.addObject("eligibilityPolicyList", eligibilityPolicyList);
        modelAndView.setViewName("views/eligibilitypolicies/viewEligibilityPolicies");
        return modelAndView;
    }

    @GetMapping(value = {"/new"})
    public ModelAndView newEligibilityPolicy() {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityPolicy eligibilityPolicy = new EligibilityPolicy();
        modelAndView.addObject("eligibilityPolicy", eligibilityPolicy);
        List<EligibilityParameter> eligibilityParameterList = eligibilityPolicyService.getParameters();
        modelAndView.addObject("allEligibilityParameterList", eligibilityParameterList);
        modelAndView.setViewName("views/eligibilitypolicies/newEligibilityPolicy");
        return modelAndView;
    }

    @PostMapping(value = {"/add"})
    public String addEligibilityPolicy(@RequestParam("action")String action, @ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy, Model model) {
        if(action.equalsIgnoreCase("save")) {
            eligibilityPolicy.setStatus("INACTIVE");
        } else if (action.equalsIgnoreCase("save & request approval")) {
            eligibilityPolicy.setStatus("PENDING");
        }
        eligibilityPolicy.setCreateDate(LocalDate.now());
        boolean insertStatus = eligibilityPolicyService.insertEligibilityPolicy(eligibilityPolicy);
        model.addAttribute("insertStatus", insertStatus);
        return "redirect:/eligibilityPolicy/";
    }

    @GetMapping(value = {"/get/{policyCode}"})
    public ModelAndView showOneEligibilityPolicy(@PathVariable("policyCode") String policyCode) {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyService.getOneEligibilityPolicy(policyCode);
        eligibilityPolicy.setEligibilityParameterList(eligibilityPolicyService.getParameters());
        modelAndView.addObject("eligibilityPolicy", eligibilityPolicy);
        modelAndView.setViewName("views/eligibilitypolicies/viewOneEligibilityPolicy");
        return modelAndView;
    }

    @PostMapping(value = {"/get/updateStatus/{policyCode}"})
    public String updateStatus(@PathVariable("policyCode") String policyCode, @RequestParam("action")String action, Model model) {
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "APPROVED";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "REJECTED";
        } else
            newStatus = "PENDING";
        boolean updateStatus = eligibilityPolicyService.updateStatus(policyCode, newStatus);
        model.addAttribute("updateStatus", updateStatus);
        return "redirect:/eligibilityPolicy/";
    }

    @GetMapping(value = {"/delete/{policyCode}"})
    public String deletePolicy(@PathVariable("policyCode") String policyCode, Model model) {
        boolean deleteStatus = eligibilityPolicyService.deleteEligibilityPolicy(policyCode);
        model.addAttribute("deleteStatus", deleteStatus);
        return "redirect:/eligibilityPolicy/";
    }
}
