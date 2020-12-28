package com.nucleus.eligibilitypolicy.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibilitypolicy.service.EligibilityPolicyService;
import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a Controller for all
 * Eligibility Policy related operations.
 *
 */
@Controller
@PropertySource("classpath:status.properties")
@RequestMapping("eligibilityPolicy")
public class EligibilityPolicyController {

    @Autowired
    EligibilityPolicyService eligibilityPolicyService;

    @Autowired
    EligibilityParameterDAO eligibilityParameterService;

    //Getting status field values from status.properties file:
    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.saved}"))
    private String saved;

    /**
     * This method is used to display a list of existing Eligibility Policies.
     *
     * @return ModelAndView This returns a view with a list of all Eligibility Policies.
     */
    @GetMapping(value = {"/", ""})
    public ModelAndView showAllEligibilityPolicies() {
        ModelAndView modelAndView = new ModelAndView();
        List<EligibilityPolicy> eligibilityPolicyList = eligibilityPolicyService.getAllEligibilityPolicies();
        modelAndView.addObject("eligibilityPolicyList", eligibilityPolicyList);
        modelAndView.setViewName("views/eligibilitypolicies/viewEligibilityPolicies");
        return modelAndView;
    }

    /**
     * This method is used to display the form for adding a new Eligibility Policy.
     *
     * @return ModelAndView This returns a form view.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/new"})
    public ModelAndView newEligibilityPolicy() {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityPolicy eligibilityPolicy = new EligibilityPolicy();
        List<EligibilityParameter> eligibilityParameterList = eligibilityParameterService.getApprovedParameters();
        modelAndView.addObject("eligibilityPolicy", eligibilityPolicy);
        modelAndView.addObject("allEligibilityParameterList", eligibilityParameterList);
        modelAndView.setViewName("views/eligibilitypolicies/newEligibilityPolicy");
        return modelAndView;
    }

    /**
     * This method is used to add an Eligibility Policy (entered by the user) to the database.
     *
     * @param action This holds one of two actions,
     *               (i.e. Save/Save & Request Approval), that decide the status.
     * @param parameterCountString This holds the count of parameters
     *                             that a particular policy will contain.
     * @param eligibilityPolicy This is the modelAttribute received from the form.
     * @param result This contains data validation errors, if any.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PostMapping(value = {"/add"})
    public String addEligibilityPolicy(@RequestParam("action")String action,
                                       @RequestParam("count")String parameterCountString,
                                       @Valid @ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy,
                                       BindingResult result,
                                       Model model) {
        //Annotation based data validation:
        if (result.hasErrors()) {
            List<EligibilityParameter> eligibilityParameterList = eligibilityParameterService.getApprovedParameters();
            model.addAttribute("allEligibilityParameterList", eligibilityParameterList);
            eligibilityPolicy.setEligibilityParameterCodes(new String[]{});
            return "views/eligibilitypolicies/newEligibilityPolicy";
        }

        //Setting "status" for the new Eligibility Policy based on button clicked:
        if(action.equalsIgnoreCase("save")) {
            eligibilityPolicy.setStatus(saved);
        } else if (action.equalsIgnoreCase("save & request approval")) {
            eligibilityPolicy.setStatus(pending);
        }
        //Setting "createdDate" field:
        eligibilityPolicy.setCreateDate(LocalDate.now());

        //Setting "createdBy" field:
        eligibilityPolicy.setCreatedBy(getPrincipal());

        //Populating Eligibility Parameters List based on the codes that user selected:
        List<EligibilityParameter> eligibilityParameters = new ArrayList<>();
        if(parameterCountString != null && eligibilityPolicy.getEligibilityParameterCodes()!=null) {
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
        model.addAttribute("policyCode", eligibilityPolicy.getPolicyCode());
        if(insertStatus) {
            model.addAttribute("operation", "Created");
            return "views/eligibilitypolicies/eligibilityPolicySuccess";
        } else {
            model.addAttribute("operation", "Creation Unsuccessful!");
            model.addAttribute("errorMsg", "Policy already exists with this policy code.");
            return "views/eligibilitypolicies/eligibilityPolicyFailure";
        }
    }

    /**
     * This method is used to display details of one Eligibility Policy
     * based on user's selection of hyperlink of code.
     *
     * @param policyCode This holds policy code of the Eligibility Policy
     *                   that has to be displayed.
     *
     * @return ModelAndView This returns a form view with read-only details.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = {"/get/{policyCode}"})
    public ModelAndView showOneEligibilityPolicy(@PathVariable("policyCode") String policyCode) {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyService.getOneEligibilityPolicy(policyCode);
        modelAndView.addObject("eligibilityPolicy", eligibilityPolicy);
        modelAndView.setViewName("views/eligibilitypolicies/viewOneEligibilityPolicy");
        return modelAndView;
    }

    /**
     * This method is used to update status {Approve, Reject} of already existing Eligibility Policy.
     *
     * @param policyCode This holds policy code of the Eligibility Policy
     *                   whose status has to be updated.
     * @param action This holds one of two actions,
     *               (i.e. Approve/Reject), that decide the status.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PostMapping(value = {"/get/updateStatus/{policyCode}"})
    public String updateStatus(@PathVariable("policyCode") String policyCode, @RequestParam("action")String action, Model model) {
        String authorizedBy = getPrincipal();
        boolean updateStatus = eligibilityPolicyService.updateStatus(policyCode, action, authorizedBy);
        model.addAttribute("policyCode", policyCode);
        if(updateStatus) {
            model.addAttribute("operation", "Status Updated");
            return "views/eligibilitypolicies/eligibilityPolicySuccess";
        } else {
            model.addAttribute("operation", "Status Update Unsuccessful!");
            return "views/eligibilitypolicies/eligibilityPolicyFailure";
        }
    }

    /**
     * This method is used to display editable details of existing Eligibility Policy.
     *
     * @param policyCode This holds policy code of the Eligibility Policy
     *                   which is going to be edited.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/edit/{policyCode}"})
    public String getEditPolicyPage(@PathVariable("policyCode") String policyCode, Model model) {
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyService.getOneEligibilityPolicy(policyCode);
        List<EligibilityParameter> existingParameterList = eligibilityPolicy.getEligibilityParameterList();
        List<EligibilityParameter> allEligibilityParameterList = eligibilityParameterService.getApprovedParameters();
        model.addAttribute("eligibilityPolicy", eligibilityPolicy);
        model.addAttribute("existingParameterList", existingParameterList);
        model.addAttribute("allEligibilityParameterList", allEligibilityParameterList);
        return "views/eligibilitypolicies/editOneEligibilityPolicy";
    }

    /**
     * This method is used to update Eligibility Policy after user has edited the fields.
     *
     * @param action This holds one of two actions,
     *               (i.e. Save/Save & Request Approval), that decide the status.
     * @param parameterCountString This holds the count of parameters
     *                             that a particular policy will contain.
     * @param eligibilityPolicy This is the modelAttribute received from the form after editing.
     * @param result This contains data validation errors, if any.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view according to the data received.
     */
    @PostMapping(value = {"edit/addEdited"})
    public String addEditedEligibilityPolicy(@RequestParam("action")String action,
                                             @RequestParam("count")String parameterCountString,
                                             @Valid @ModelAttribute("eligibilityPolicy") EligibilityPolicy eligibilityPolicy,
                                             BindingResult result,
                                             Model model) {

        //Annotation based data validation:
        if (result.hasErrors()) {
            List<EligibilityParameter> eligibilityParameterList = eligibilityParameterService.getApprovedParameters();
            EligibilityPolicy eligibilityPolicyOld = eligibilityPolicyService.getOneEligibilityPolicy(eligibilityPolicy.getPolicyCode());
            List<EligibilityParameter> existingParameterList = eligibilityPolicyOld.getEligibilityParameterList();
            model.addAttribute("allEligibilityParameterList", eligibilityParameterList);
            model.addAttribute("existingParameterList", existingParameterList);
            eligibilityPolicy.setEligibilityParameterCodes(new String[]{});
            return "views/eligibilitypolicies/editOneEligibilityPolicy";
        }

        //Setting "status" for the new Eligibility Policy based on button clicked:
        if(action.equalsIgnoreCase("save")) {
            eligibilityPolicy.setStatus(saved);
        } else if (action.equalsIgnoreCase("save & request approval")) {
            eligibilityPolicy.setStatus(pending);
        }
        //Setting "modifiedDate" field:
        eligibilityPolicy.setModifiedDate(LocalDate.now());

        //Setting "modifiededBy" field:
        eligibilityPolicy.setModifiedBy(getPrincipal());

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
        model.addAttribute("policyCode", eligibilityPolicy.getPolicyCode());
        if(editStatus) {
            model.addAttribute("operation", "Edited");
            return "views/eligibilitypolicies/eligibilityPolicySuccess";
        } else {
            model.addAttribute("operation", "Edit Unsuccessful!");
            return "views/eligibilitypolicies/eligibilityPolicyFailure";
        }
    }

    /**
     * This method is used to delete an existing Eligibility Policy from database.
     *
     * @param policyCode This holds policy code of the Eligibility Policy
     *                   which is going to be deleted.
     * @param model This model object is passed to the next pages.
     *
     * @return String This returns path to a view with list of all policies.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = {"/delete/{policyCode}"})
    public String deletePolicy(@PathVariable("policyCode") String policyCode, Model model) {
        boolean deleteStatus = eligibilityPolicyService.deleteEligibilityPolicy(policyCode);
        model.addAttribute("policyCode", policyCode);
        if(deleteStatus) {
            model.addAttribute("operation", "Deleted");
            return "views/eligibilitypolicies/eligibilityPolicySuccess";
        } else {
            model.addAttribute("operation", "Deletion Unsuccessful!");
            return "views/eligibilitypolicies/eligibilityPolicyFailure";
        }
    }

    //Method to get username:
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
