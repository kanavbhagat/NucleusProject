package com.nucleus.eligibiltyparameter.controller;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import com.nucleus.eligibiltyparameter.service.EligibilityParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@PropertySource("classpath:status.properties")
@RequestMapping("/main")
public class EligibilityParameterController {

    @Autowired
    private EligibilityParameterService eligibilityParameterService;

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
     * Getting All eligibility parameters from database in maker and checker screens
     * @param (model) which stores list of eligibility parameters
     * @return maker screen or checker screen according to login credentials
     */
    @RequestMapping("/eligibilityparameter")
    public String getParameters(Model model) {
        List<EligibilityParameter>list= eligibilityParameterService.getAll();
        model.addAttribute("parameters",list);
        return "views/eligibilityparameters/viewEligibilityParameters";
    }

    /**
     * Creating a new Eligibility Parameter in maker screen
     * @param (model) which stores object of eligibility parameter
     * @return jsp page which creates a new eligibility parameter
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping("/createparameter")
    public String createParameter(Model model){
        EligibilityParameter eligibilityParameter = new EligibilityParameter();
        model.addAttribute("eligibilityParameter", eligibilityParameter);
        return "views/eligibilityparameters/createParameter";
    }

    /**
     * Saving a Parameter in database
     * @param eligibilityParameter object of eligibility parameter
     * @return success page if no error, same page (new Eligibility Parameter creation page) if error
     */
    @PostMapping(value = "/insertparameter", params = "action1")
    public String saveParameter(@Valid @ModelAttribute("eligibilityParameter") EligibilityParameter eligibilityParameter,
                                BindingResult br, Model model){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/createParameter";
        }
        else
        {
            eligibilityParameter.setCreatedBy(getPrincipal());
            eligibilityParameter.setCreateDate(LocalDate.now());
            eligibilityParameter.setStatus(saved);
            String pcode=eligibilityParameter.getParameterCode();
            boolean success=eligibilityParameterService.insertParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            model.addAttribute("status","Inserted");
            model.addAttribute("error","inserting");
            if(success) {
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }
            else {
                model.addAttribute("message","Eligibility Parameter already exists for this parameter code");
                return "views/eligibilityparameters/eligibilityparameterfailure";
            }
        }

    }

    /**
     * Saving parameter in database and requesting for approval by checker
     * @param eligibilityParameter object of eligibility parameter
     * @return success page if no error, same page (new Eligibility Parameter creation page) if error
     */
    @PostMapping(value = "/insertparameter", params = "action2")
    public String saveAndRequestApproval(@Valid @ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter,
                                         BindingResult br, Model model){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/createParameter";
        }
        else
        {
            eligibilityParameter.setCreatedBy(getPrincipal());
            eligibilityParameter.setCreateDate(LocalDate.now());
            eligibilityParameter.setStatus(pending);
            String pcode=eligibilityParameter.getParameterCode();
            boolean success=eligibilityParameterService.insertParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            model.addAttribute("status","Inserted");
            model.addAttribute("error","inserting");
            if(success) {
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }
            else {
                model.addAttribute("message","Eligibility Parameter already exists for this parameter code");
                return "views/eligibilityparameters/eligibilityparameterfailure";
            }
        }

    }

    /**
     * Editing an Eligibility Parameter and saving it into database
     * @param eligibilityParameter object of eligibility parameter
     * @return success page if no error, same page (Eligibility Parameter edit page) if error
     */
    @PostMapping(value = "/edit/editparameter", params = "action1")
    public String editParameter1(@Valid @ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter,
                                 BindingResult br, Model model){

        if(br.hasErrors())
        {
            return "views/eligibilityparameters/editParameter";
        }
        else
        {
            String pcode=eligibilityParameter.getParameterCode();
            eligibilityParameter.setModifiedBy(getPrincipal());
            eligibilityParameter.setStatus(saved);
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            model.addAttribute("status","Edited");
            model.addAttribute("error","editing");
            if(valid)
            {
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }
            else
            {
                return "views/eligibilityparameters/eligibilityparameterfailure";
            }
        }

    }

    /**
     * Editing an eligibility parameter and saving it into database and requesting a approval by checker
     * @param eligibilityParameter object of eligibility parameter
     * @param br
     * @return success page if no error, same page (Eligibility Parameter edit page) if error
     */
    @PostMapping(value = "/edit/editparameter", params = "action2")
    public String editParameter2(@Valid @ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter,
                                 BindingResult br, Model model){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/editParameter";
        }
        else
        {
            String pcode=eligibilityParameter.getParameterCode();
            eligibilityParameter.setModifiedBy(getPrincipal());
            eligibilityParameter.setStatus(pending);
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            model.addAttribute("status","Edited");
            model.addAttribute("error","editing");
            if(valid)
            {
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }
            else
            {
                return "views/eligibilityparameters/eligibilityparameterfailure";
            }
        }
    }

    /**
     * Deleting a particular eligibility parameter from database
     * @param parameterCode which is used to delete eligibility parameter
     * @return maker screen
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping("/delete/{parameterCode}")
    public String deleteParameter(@PathVariable("parameterCode") String parameterCode,Model model) {
        String pcode=parameterCode;
        boolean success = eligibilityParameterService.deleteEligibilityParameter(parameterCode);
        model.addAttribute("parameterCode",pcode);
        model.addAttribute("status","Deleted");
        model.addAttribute("error","deleting");
        if(success) {
            return "views/eligibilityparameters/eligibilityparametersuccess";
        }
        else {
            return "views/eligibilityparameters/eligibilityparameterfailure";
        }
    }

    /**
     * Edit parameter screen
     * @param parameterCode to edit particular eligibility parameter
     * @param model to store eligibility parameter object
     * @return edit eligibility parameter page
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping("/edit/{parameterCode}")
    public ModelAndView editParameter(@PathVariable("parameterCode") String parameterCode,Model model){

        ModelAndView modelAndView = new ModelAndView();
        EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(parameterCode);
        modelAndView.addObject("eligibilityParameter1", eligibilityParameter);
        modelAndView.setViewName("views/eligibilityparameters/editParameter");
        return modelAndView;
    }

    /**
     * Getting a single parameter from database for approval or rejection
     * @param (parameterCode) to show details of particular eligibility parameter
     * @return page containing details of a particular eligibility parameter
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = {"/get/{parameterCode}"})
    public ModelAndView showOneEligibilityParameter(@PathVariable("parameterCode") String parameterCode) {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(parameterCode);
        modelAndView.addObject("eligibilityParameter2", eligibilityParameter);
        modelAndView.setViewName("views/eligibilityparameters/viewOneEligibilityParameter");
        return modelAndView;
    }

    /**
     * Updating status of a particular eligibility parameter as updated or rejected into database
     * @param  parameterCode to update status of a particular eligibility parameter
     * @param action to update status of eligibility parameter according to action
     * @return checker screen
     */
    @PostMapping(value = {"/updateStatus/{parameterCode}"})
    public String updateStatus(@PathVariable("parameterCode") String parameterCode, @RequestParam("action")String action,Model model) {
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = approved;
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = rejected;
        } else {
            newStatus = pending;
        }
        String authorizedBy = getPrincipal();
        boolean success = eligibilityParameterService.updateStatus(parameterCode, newStatus,authorizedBy);
        model.addAttribute("parameterCode",parameterCode);
        model.addAttribute("status","Updated");
        model.addAttribute("error","updating");
        if(success) {
            return "views/eligibilityparameters/eligibilityparametersuccess";
        }
        else {
            return "views/eligibilityparameters/eligibilityparameterfailure";
        }
    }

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
