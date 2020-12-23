package com.nucleus.eligibiltyparameter.controller;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import com.nucleus.eligibiltyparameter.service.EligibilityParameterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/main")
public class EligibilityParameterController {

    @Autowired
    private EligibilityParameterService eligibilityParameterService;

    /**
     * Getting All eligibility parameters from database in maker and checker screens
     * @param (model) which stores list of eligibility parameters
     * @return maker screen or checker screen according to login credentials
     */
    @RequestMapping("/eligibilityparameter")
    public String getParameters(Model model) {
        System.out.println("kirtika");
        List<EligibilityParameter>list= eligibilityParameterService.getAll();
        model.addAttribute("parameters",list);
        System.out.println(list);
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
     * @param br
     * @return success page if no error, same page (new Eligibility Parameter creation page) if error
     */
    @RequestMapping(value = "/insertparameter", params = "action1",method = RequestMethod.POST)
    public String saveParameter(@Valid @ModelAttribute("eligibilityParameter") EligibilityParameter eligibilityParameter, BindingResult br,Model model){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/createParameter";
        }
        else
        {
            eligibilityParameter.setCreatedBy(getPrincipal());
            eligibilityParameter.setCreateDate(LocalDate.now());
            eligibilityParameter.setStatus("Saved");
            String pcode=eligibilityParameterService.insertParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            return "views/eligibilityparameters/eligibilityparametersuccess";
        }

    }

    /**
     * Saving parameter in database and requesting for approval by checker
     * @param eligibilityParameter object of eligibility parameter
     * @param br
     * @return success page if no error, same page (new Eligibility Parameter creation page) if error
     */
    @RequestMapping(value = "/insertparameter", params = "action2",method = RequestMethod.POST)
    public String saveAndRequestApproval(@Valid @ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter,BindingResult br,Model model){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/createParameter";
        }
        else
        {
            eligibilityParameter.setCreatedBy(getPrincipal());
            eligibilityParameter.setCreateDate(LocalDate.now());
            eligibilityParameter.setStatus("Pending");
            String pcode=eligibilityParameterService.insertParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            return "views/eligibilityparameters/eligibilityparametersuccess";
        }

    }

    /**
     * Editing an Eligibility Parameter and saving it into database
     * @param eligibilityParameter object of eligibility parameter
     * @param br
     * @return success page if no error, same page (Eligibility Parameter edit page) if error
     */
    @RequestMapping(value = "/edit/editparameter", params = "action1",method = RequestMethod.POST)
    public String editParameter1(@Valid @ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter,BindingResult br,Model model){

        if(br.hasErrors())
        {
            return "views/eligibilityparameters/editParameter";
        }
        else
        {
            String pcode=eligibilityParameter.getParameterCode();
            eligibilityParameter.setModifiedBy(getPrincipal());
            eligibilityParameter.setStatus("Saved");
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            if(valid==true)
            {
                System.out.println("true");
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }

            else
            {
                System.out.println("false");
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
    @RequestMapping(value = "/edit/editparameter", params = "action2",method = RequestMethod.POST)
    public String editParameter2(@Valid @ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter,BindingResult br,Model model){
        if(br.hasErrors())
        {
            return "views/eligibilityparameters/editParameter";
        }
        else
        {
            String pcode=eligibilityParameter.getParameterCode();
            eligibilityParameter.setModifiedBy(getPrincipal());
            eligibilityParameter.setStatus("Pending");
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);
            model.addAttribute("parameterCode",pcode);
            if(valid==true)
            {
                System.out.println("true");
                return "views/eligibilityparameters/eligibilityparametersuccess";
            }

            else
            {
                System.out.println("false");
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
    public String deleteParameter(@PathVariable("parameterCode") String parameterCode) {
        String pcode = eligibilityParameterService.deleteEligibilityParameter(parameterCode);
        return "redirect:/main/eligibilityparameter/";

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
    public String updateStatus(@PathVariable("parameterCode") String parameterCode, @RequestParam("action")String action) {
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "Approved";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "Rejected";
        } else
            newStatus = "Pending";

            String authorizedBy = getPrincipal();
        boolean updateStatus = eligibilityParameterService.updateStatus(parameterCode, newStatus,authorizedBy);
        return "redirect:/main/eligibilityparameter/";
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
