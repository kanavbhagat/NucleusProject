package com.nucleus.eligibiltyparameter.controller;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import com.nucleus.eligibiltyparameter.service.EligibilityParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/main")
public class EligibilityParameterController {

    @Autowired
    private EligibilityParameterService eligibilityParameterService;

    @RequestMapping("/getmaker")
    public String getParametersMaker(Model model) {
        System.out.println("kirtika");
        List<EligibilityParameter>list= eligibilityParameterService.getAll();
        model.addAttribute("parameters",list);
        System.out.println(list);
        return "views/eligibilityparameters/parameterMaker";
    }

    @GetMapping("/createparameter")
    public String createParameter(Model model){
        EligibilityParameter eligibilityParameter = new EligibilityParameter();

        model.addAttribute("eligibilityParameter", eligibilityParameter);
        return "views/eligibilityparameters/createParameter";
    }

    @RequestMapping(value = "/insertparameter", params = "action1",method = RequestMethod.POST)
    public String saveParameter(@ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter){
        System.out.println("action1");
        eligibilityParameter.setCreatedBy("Kirtika");
        eligibilityParameter.setCreateDate(LocalDate.now());
        eligibilityParameter.setStatus("Inactive");
        eligibilityParameterService.insertParameter(eligibilityParameter);
        return "views/eligibilityparameters/eligibilityparametersuccess";

    }

    @RequestMapping(value = "/insertparameter", params = "action2",method = RequestMethod.POST)
    public String saveAndRequestApproval(@ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter){
        System.out.println("action2");
        eligibilityParameter.setCreatedBy("Kirtika");
        eligibilityParameter.setCreateDate(LocalDate.now());
        eligibilityParameter.setStatus("Pending");
        eligibilityParameterService.insertParameterAndRequestApproval(eligibilityParameter);
        return "views/eligibilityparameters/eligibilityparametersuccess";

    }

    @RequestMapping(value = "/editparameter", params = "action1",method = RequestMethod.POST)
    public String editParameter1(@ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter){
        System.out.println("inside editparameter");

            eligibilityParameter.setModifiedBy("Kirtika");
            eligibilityParameter.setStatus("Inactive");
            boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);

        System.out.println("action1");
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

    @RequestMapping(value = "/editparameter", params = "action2",method = RequestMethod.POST)
    public String editParameter2(@ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter){
        System.out.println("inside editparameter");

        eligibilityParameter.setModifiedBy("Kirtika");
        eligibilityParameter.setStatus("Pending");
        boolean valid=eligibilityParameterService.editParameter(eligibilityParameter);

        System.out.println("action2");
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

    @RequestMapping("/getchecker")
    public String getParametersChecker(Model model) {
        System.out.println("kirtika");
        List<EligibilityParameter>list= eligibilityParameterService.getAll();
        model.addAttribute("parameters",list);
        System.out.println(list);
        return "views/eligibilityparameters/parameterChecker";
    }

    @RequestMapping("/delete/{parameterCode}")
    public String deleteParameter(@PathVariable("parameterCode") String parameterCode, Model model) {
        boolean deleteStatus = eligibilityParameterService.deleteEligibilityParameter(parameterCode);
        model.addAttribute("deleteStatus", deleteStatus);
        return "redirect:/main/getmaker/";

    }

    @RequestMapping("/edit/{parameterCode}/{parameterName}/{parameterDescription}/{minValue}/{maxValue}")
    public String editParameter(@PathVariable("parameterCode") String parameterCode,@PathVariable("parameterName") String parameterName,
                                @PathVariable("parameterDescription") String parameterDescription,@PathVariable("minValue") String minValue,
                                @PathVariable("maxValue") String maxValue,@ModelAttribute("eligibilityParameter1")EligibilityParameter eligibilityParameter) {

        return "views/eligibilityparameters/editParameter";
    }

    @GetMapping(value = {"/get/{parameterCode}"})
    public ModelAndView showOneEligibilityParameter(@PathVariable("parameterCode") String parameterCode) {
        ModelAndView modelAndView = new ModelAndView();
        EligibilityParameter eligibilityParameter = eligibilityParameterService.getOneEligibilityParameter(parameterCode);
        modelAndView.addObject("eligibilityParameter2", eligibilityParameter);
        modelAndView.setViewName("views/eligibilityparameters/viewOneEligibilityParameter");
        return modelAndView;
    }

    @PostMapping(value = {"/updateStatus/{parameterCode}"})
    public String updateStatus(@PathVariable("parameterCode") String parameterCode, @RequestParam("action")String action, Model model) {
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "Approved";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "Rejected";
        } else
            newStatus = "Pending";
        System.out.println(newStatus);
        boolean updateStatus = eligibilityParameterService.updateStatus(parameterCode, newStatus);
        model.addAttribute("updateStatus", updateStatus);
        return "redirect:/main/getchecker/";
    }

}

