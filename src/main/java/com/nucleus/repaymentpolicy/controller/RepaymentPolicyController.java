package com.nucleus.repaymentpolicy.controller;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RepaymentPolicyController {

    @Autowired
    RepaymentPolicyService repaymentPolicyService;

    /**
     * Handles and retrieves all RepaymentPolicy and show it in a JSP page allRepaymentPoliciesData
     *
     * @return the view containing allRepaymentPoliciesData.jsp
     */
    @RequestMapping(value = "/showRepaymentPolicy", method = RequestMethod.GET)
    public ModelAndView getNewRepaymentPolicies(Model model) {
        List<RepaymentPolicy> newRepaymentPolicies = repaymentPolicyService.getRepaymentPolicyList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/allRepaymentPoliciesData");
        mv.addObject("newRepaymentPolicies", newRepaymentPolicies);
        return mv;
        //model.addAttribute("newRepaymentPolicies", newRepaymentPolicies);
        //return "views/repaymentpolicy/allRepaymentPoliciesData";
    }

    @RequestMapping(value = "/showRepaymentPolicy/add", method = RequestMethod.GET)
    public ModelAndView getAdd(Model model) {
        //model.addAttribute("newRepaymentPolicy", new RepaymentPolicy());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
        mv.addObject("newRepaymentPolicy", new RepaymentPolicy());
        return mv;
    }

    @RequestMapping(params ="save",value = "/showRepaymentPolicy/add", method = RequestMethod.POST)
    public ModelAndView add(@Valid RepaymentPolicy repaymentPolicy, BindingResult result, ModelMap map) {
        if(result.hasErrors())
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
            return mv;
            //return "views/repaymentpolicy/newRepaymentPolicyScreenMaker";
        }
        String policyCode = repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(repaymentPolicy.getPolicyCode(),"Incomplete");
        repaymentPolicyService.updateCreationParameters(policyCode,"Creator");
        //map.addAttribute("policyCode",policyCode);
        //return "views/repaymentpolicy/addedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/addedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @RequestMapping(params ="saveApprove",value = "/showRepaymentPolicy/add", method = RequestMethod.POST)
    public ModelAndView addAndSendForApproval(@Valid RepaymentPolicy repaymentPolicy, BindingResult result, ModelMap map) {
        if(result.hasErrors())
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
            return mv;
            //return "views/repaymentpolicy/newRepaymentPolicyScreenMaker";
        }
        String policyCode = repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(repaymentPolicy.getPolicyCode(),"Pending");
        repaymentPolicyService.updateCreationParameters(policyCode,"Creator");
        //map.addAttribute("policyCode",policyCode);
        //return "views/repaymentpolicy/addedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/addedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @RequestMapping(value = "/showRepaymentPolicy/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value="policyCode", required=true) String policyCode,
                         Model model) {
        repaymentPolicyService.deleteRepaymentPolicy(policyCode);
        //model.addAttribute("policyCode", policyCode);
        //return "views/repaymentpolicy/deletedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/deletedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @RequestMapping(value = "/showRepaymentPolicy/edit", method = RequestMethod.GET)
    public ModelAndView getEdit(@RequestParam(value="policyCode", required=true) String policyCode,
                          Model model) {
        //model.addAttribute("newRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        //return "views/repaymentpolicy/editpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editpage");
        mv.addObject("newRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        return mv;
    }

    @RequestMapping(params ="update",value = "/showRepaymentPolicy/edit", method = RequestMethod.POST)
    public ModelAndView saveEdit(@ModelAttribute("newRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                           @RequestParam(value="policyCode", required=true) String policyCode,
                           Model model) {

        repaymentPolicy.setPolicyCode(policyCode);
        repaymentPolicyService.updateRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(policyCode,"Incomplete");
        repaymentPolicyService.updateModificationParameters(policyCode,"Modifier");
        //model.addAttribute("policyCode", policyCode);
        //return "views/repaymentpolicy/editedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @RequestMapping(params ="updateApprove",value = "/showRepaymentPolicy/edit", method = RequestMethod.POST)
    public ModelAndView saveApproveEdit(@ModelAttribute("newRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                                  @RequestParam(value="policyCode", required=true) String policyCode,
                                  Model model) {

        repaymentPolicy.setPolicyCode(policyCode);
        repaymentPolicyService.updateRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(policyCode,"Pending");
        repaymentPolicyService.updateModificationParameters(policyCode,"Modifier");
        //model.addAttribute("policyCode", policyCode);
        //return "views/repaymentpolicy/editedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @RequestMapping(value = "/showRepaymentPolicy/check", method = RequestMethod.GET)
    public ModelAndView getCheck(@RequestParam(value="policyCode", required=true) String policyCode,
                           Model model) {

        //model.addAttribute("checkRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        //return "views/repaymentpolicy/newRepaymentPolicyScreenChecker";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenChecker");
        mv.addObject("checkRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        return mv;
    }

    @RequestMapping(params ="approve" ,value = "/showRepaymentPolicy/check", method = RequestMethod.POST)
    public ModelAndView approve(@ModelAttribute("checkRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                          @RequestParam(value="policyCode", required=true) String policyCode,
                          Model model) {

        repaymentPolicyService.changeStatus(policyCode,"Approved");
        repaymentPolicyService.updateAuthorizationParameters(policyCode,"Authorizer");
        //model.addAttribute("policyCode", policyCode);
        //return "views/repaymentpolicy/editedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editedpage");
        mv.addObject("policyCode", policyCode);
        return mv;

    }
    @RequestMapping(params ="reject" ,value = "/showRepaymentPolicy/check", method = RequestMethod.POST)
    public ModelAndView reject(@ModelAttribute("checkRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                         @RequestParam(value="policyCode", required=true) String policyCode,
                         Model model) {

        repaymentPolicyService.changeStatus(policyCode,"Rejected");
        repaymentPolicyService.updateAuthorizationParameters(policyCode,"Authorizer");
        //model.addAttribute("policyCode", policyCode);
        //return "views/repaymentpolicy/editedpage";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }




//    @RequestMapping("/showRepaymentPolicy")
//    public ModelAndView showRepaymentPolicyList()
//    {
//        //repaymentPolicyService.addTempRepaymentPolicy();
//        List<RepaymentPolicy> policyList =  repaymentPolicyService.getRepaymentPolicyList();
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("views/repaymentpolicy/repaymentPolicyMaker");
//        mv.addObject("policyList",policyList);
//        return mv;
//    }

//    @GetMapping("/editRepaymentPolicy/{policyCode}")
//    public String editRepaymentPolicy(@PathVariable String policyCode)
//    {
//        return "Edit Policy "+ policyCode;
//    }
//
//    @GetMapping("/deleteRepaymentPolicy/{policyCode}")
//    public String deleteRepaymentPolicy(@PathVariable String policyCode)
//    {
//        return "Delete Policy "+ policyCode;
//    }
}