package com.nucleus.eligibilitypolicy.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("eligibilityPolicy")
public class EligibilityPolicyController {

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;

    @Autowired
    EligibilityParameterDAO eligibilityParameterService;

    public EligibilityPolicyService getEligibilityPolicyService() {
        return eligibilityPolicyService;
    }

    public void setEligibilityPolicyService(EligibilityPolicyService eligibilityPolicyService) {
        this.eligibilityPolicyService = eligibilityPolicyService;
    }

    //To display a list of existing Eligibility Policies:
    @GetMapping(value = {"/", ""})
    public ModelAndView showAllEligibilityPolicies() {
        ModelAndView modelAndView = new ModelAndView();
        List<EligibilityPolicy> eligibilityPolicyList = eligibilityPolicyService.getAllEligibilityPolicies();
        modelAndView.addObject("eligibilityPolicyList", eligibilityPolicyList);
        modelAndView.setViewName("views/eligibilitypolicies/viewEligibilityPolicies");
        return modelAndView;
    }

    //To display the form for adding a new Eligibility Policy:
    @GetMapping(value = {"/new"})
    public ModelAndView newEligibilityPolicy() {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityPolicy eligibilityPolicy = new EligibilityPolicy();
        List<EligibilityParameter> eligibilityParameterList = eligibilityParameterService.getAll();
        modelAndView.addObject("eligibilityPolicy", eligibilityPolicy);
        modelAndView.addObject("allEligibilityParameterList", eligibilityParameterList);
        modelAndView.setViewName("views/eligibilitypolicies/newEligibilityPolicy");
        return modelAndView;
    }

    //To add an Eligibility Policy (entered by the user) to the database:
    @PostMapping(value = {"/add"})
    public String addEligibilityPolicy(@RequestParam("action")String action,
                                       @RequestParam("count")String parameterCountString,
                                       @Valid @ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy,
                                       BindingResult result,
                                       Model model) {
        //Annotation based data validation:
        if (result.hasErrors()) {
            List<EligibilityParameter> eligibilityParameterList = eligibilityParameterService.getAll();
            model.addAttribute("allEligibilityParameterList", eligibilityParameterList);
            return "views/eligibilitypolicies/newEligibilityPolicy";
        }

        //Setting "status" for the new Eligibility Policy based on button clicked:
        if(action.equalsIgnoreCase("save")) {
            eligibilityPolicy.setStatus("INACTIVE");
        } else if (action.equalsIgnoreCase("save & request approval")) {
            eligibilityPolicy.setStatus("PENDING");
        }
        //Setting "createdDate" field:
        eligibilityPolicy.setCreateDate(LocalDate.now());

        //Populating Eligibility Parameters List based on the codes that user selected:
        List<EligibilityParameter> eligibilityParameters = new ArrayList<>();
        if(parameterCountString != null) {
            int parameterCount = Integer.parseInt(parameterCountString);
            for (int i = 0; i < parameterCount; i++) {
                EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(eligibilityPolicy.getEligibilityParameterCodes()[i]);
                if (eligibilityParameter != null && !eligibilityParameters.contains(eligibilityParameter)) {
                    eligibilityParameters.add(eligibilityParameter);
                }
            }
            eligibilityPolicy.setEligibilityParameterList(eligibilityParameters);
        }
        //Adding the new Eligibility Policy object to database and getting a true/false based response:
        boolean insertStatus = eligibilityPolicyService.insertEligibilityPolicy(eligibilityPolicy);
        model.addAttribute("insertStatus", insertStatus);
        return "redirect:/eligibilityPolicy/";
    }

    //To display details of one Eligibility Policy based on user's selection of hyperlink of code:
    @GetMapping(value = {"/get/{policyCode}"})
    public ModelAndView showOneEligibilityPolicy(@PathVariable("policyCode") String policyCode) {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyService.getOneEligibilityPolicy(policyCode);
        modelAndView.addObject("eligibilityPolicy", eligibilityPolicy);
        modelAndView.setViewName("views/eligibilitypolicies/viewOneEligibilityPolicy");
        return modelAndView;
    }

    //To update status {Approve, Reject} of already existing Eligibility Policy:
    @PostMapping(value = {"/get/updateStatus/{policyCode}"})
    public String updateStatus(@PathVariable("policyCode") String policyCode, @RequestParam("action")String action, Model model) {
        boolean updateStatus = eligibilityPolicyService.updateStatus(policyCode, action);
        model.addAttribute("updateStatus", updateStatus);
        return "redirect:/eligibilityPolicy/";
    }

    //To display editable details of existing Eligibility Policy:
    @GetMapping(value = {"/edit/{policyCode}"})
    public String getEditPolicyPage(@PathVariable("policyCode") String policyCode, Model model) {
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyService.getOneEligibilityPolicy(policyCode);
        List<EligibilityParameter> existingParameterList = eligibilityPolicy.getEligibilityParameterList();
        List<EligibilityParameter> allEligibilityParameterList = eligibilityParameterService.getAll();
        model.addAttribute("eligibilityPolicy", eligibilityPolicy);
        model.addAttribute("existingParameterList", existingParameterList);
        model.addAttribute("allEligibilityParameterList", allEligibilityParameterList);
        return "views/eligibilitypolicies/editOneEligibilityPolicy";
    }

    //To update Eligibility Policy after user has edited the fields:
    @PostMapping(value = {"edit/addEdited"})
    public String addEditedEligibilityPolicy(@RequestParam("action")String action,
                                       @RequestParam("count")String parameterCountString,
                                       @Valid @ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy,
                                       BindingResult result,
                                       Model model) {
        //Annotation based data validation:
        if (result.hasErrors()) {
            List<EligibilityParameter> eligibilityParameterList = eligibilityParameterService.getAll();
            EligibilityPolicy eligibilityPolicyOld = eligibilityPolicyService.getOneEligibilityPolicy(eligibilityPolicy.getPolicyCode());
            List<EligibilityParameter> existingParameterList = eligibilityPolicyOld.getEligibilityParameterList();
            model.addAttribute("allEligibilityParameterList", eligibilityParameterList);
            model.addAttribute("existingParameterList", existingParameterList);
            System.out.println(existingParameterList);
            return "views/eligibilitypolicies/editOneEligibilityPolicy";
        }

        //Setting "status" for the new Eligibility Policy based on button clicked:
        if(action.equalsIgnoreCase("save")) {
            eligibilityPolicy.setStatus("INACTIVE");
        } else if (action.equalsIgnoreCase("save & request approval")) {
            eligibilityPolicy.setStatus("PENDING");
        }
        //Setting "createdDate" field:
        eligibilityPolicy.setModifiedDate(LocalDate.now());

        //Populating Eligibility Parameters List based on the codes that user selected:
        List<EligibilityParameter> eligibilityParameters = new ArrayList<>();
        if(parameterCountString != null) {
            int parameterCount = Integer.parseInt(parameterCountString);
            for (int i = 0; i < parameterCount; i++) {
                EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(eligibilityPolicy.getEligibilityParameterCodes()[i]);
                if (eligibilityParameter != null  && !eligibilityParameters.contains(eligibilityParameter)) {
                    eligibilityParameters.add(eligibilityParameter);
                }
            }
            eligibilityPolicy.setEligibilityParameterList(eligibilityParameters);
        }
        //Adding the new Eligibility Policy object to database and getting a true/false based response:
        boolean editStatus = eligibilityPolicyService.updateEligibilityPolicy(eligibilityPolicy);
        model.addAttribute("editStatus", editStatus);
        return "redirect:/eligibilityPolicy/";
    }

    //To delete an existing Eligibility Policy from database:
    @GetMapping(value = {"/delete/{policyCode}"})
    public String deletePolicy(@PathVariable("policyCode") String policyCode, Model model) {
        boolean deleteStatus = eligibilityPolicyService.deleteEligibilityPolicy(policyCode);
        model.addAttribute("deleteStatus", deleteStatus);
        return "redirect:/eligibilityPolicy/";
    }
}
