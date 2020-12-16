package com.nucleus.eligibiltyparameter.controller;

import com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import com.nucleus.eligibiltyparameter.service.EligibilityParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "parameterMaker";
    }

    @GetMapping("/createparameter")
    public String createParameter(Model model){
        EligibilityParameter eligibilityParameter = new EligibilityParameter();

        model.addAttribute("eligibilityParameter", eligibilityParameter);
        return "createParameter";
    }

    @RequestMapping(value = "/insertparameter", params = "action1",method = RequestMethod.POST)
    public String saveParameter(@ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter){
        eligibilityParameter.setCreatedBy("Kirtika");
        eligibilityParameter.setCreateDate(LocalDate.now());
        eligibilityParameter.setStatus("Inactive");
        eligibilityParameterService.insertParameter(eligibilityParameter);
        System.out.println("action1");
        return "eligibilityparametersuccess";

    }

    @RequestMapping(value = "/insertparameter", params = "action2",method = RequestMethod.POST)
    public String saveAndRequestApproval(@ModelAttribute("eligibilityParameter")EligibilityParameter eligibilityParameter){
        System.out.println("action2");
        eligibilityParameter.setCreatedBy("Kirtika");
        eligibilityParameter.setCreateDate(LocalDate.now());
        eligibilityParameter.setStatus("Pending");
        eligibilityParameterService.insertParameterAndRequestApproval(eligibilityParameter);
        return "eligibilityparametersuccess";

    }

    @RequestMapping("/getchecker")
    public String getParametersChecker(Model model) {
        System.out.println("kirtika");
        List<EligibilityParameter>list= eligibilityParameterService.getAll();
        model.addAttribute("parameters",list);
        System.out.println(list);
        return "parameterChecker";
    }

}

