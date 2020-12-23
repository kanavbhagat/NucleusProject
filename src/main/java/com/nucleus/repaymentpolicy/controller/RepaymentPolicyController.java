package com.nucleus.repaymentpolicy.controller;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
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
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(value = "/showRepaymentPolicy/add", method = RequestMethod.GET)
    public ModelAndView getAdd(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
        mv.addObject("newRepaymentPolicy", new RepaymentPolicy());
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="save",value = "/showRepaymentPolicy/add", method = RequestMethod.POST)
    public ModelAndView add(@Valid RepaymentPolicy newRepaymentPolicy, BindingResult result, ModelMap map) {
        if(result.hasErrors())
        {
            ModelAndView mv = new ModelAndView();
            mv.addObject("newRepaymentPolicy", new RepaymentPolicy());
            mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
            return mv;
        }
        String policyCode = repaymentPolicyService.addRepaymentPolicy(newRepaymentPolicy);
        repaymentPolicyService.changeStatus(newRepaymentPolicy.getPolicyCode(),"Saved");
        repaymentPolicyService.updateCreationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/addedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="saveApprove",value = "/showRepaymentPolicy/add", method = RequestMethod.POST)
    public ModelAndView addAndSendForApproval(@Valid RepaymentPolicy repaymentPolicy, BindingResult result, ModelMap map) {
        if(result.hasErrors())
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
            return mv;
        }
        String policyCode = repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(repaymentPolicy.getPolicyCode(),"Pending");
        repaymentPolicyService.updateCreationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/addedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(value = "/showRepaymentPolicy/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value="policyCode", required=true) String policyCode,
                         Model model) {
        repaymentPolicyService.deleteRepaymentPolicy(policyCode);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/deletedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(value = "/showRepaymentPolicy/edit", method = RequestMethod.GET)
    public ModelAndView getEdit(@RequestParam(value="policyCode", required=true) String policyCode,
                          Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editpage");
        mv.addObject("newRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="update",value = "/showRepaymentPolicy/edit", method = RequestMethod.POST)
    public ModelAndView saveEdit(@ModelAttribute("newRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                           @RequestParam(value="policyCode", required=true) String policyCode,
                           Model model) {

        repaymentPolicy.setPolicyCode(policyCode);
        repaymentPolicyService.updateRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(policyCode,"Saved");
        repaymentPolicyService.updateModificationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="updateApprove",value = "/showRepaymentPolicy/edit", method = RequestMethod.POST)
    public ModelAndView saveApproveEdit(@ModelAttribute("newRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                                  @RequestParam(value="policyCode", required=true) String policyCode,
                                  Model model) {

        repaymentPolicy.setPolicyCode(policyCode);
        repaymentPolicyService.updateRepaymentPolicy(repaymentPolicy);
        repaymentPolicyService.changeStatus(policyCode,"Pending");
        repaymentPolicyService.updateModificationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @RequestMapping(value = "/showRepaymentPolicy/check", method = RequestMethod.GET)
    public ModelAndView getCheck(@RequestParam(value="policyCode", required=true) String policyCode,
                           Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenChecker");
        mv.addObject("checkRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @RequestMapping(params ="approve" ,value = "/showRepaymentPolicy/check", method = RequestMethod.POST)
    public ModelAndView approve(@ModelAttribute("checkRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                          @RequestParam(value="policyCode", required=true) String policyCode,
                          Model model) {

        repaymentPolicyService.changeStatus(policyCode,"Approved");
        repaymentPolicyService.updateAuthorizationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/approvedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @RequestMapping(params ="reject" ,value = "/showRepaymentPolicy/check", method = RequestMethod.POST)
    public ModelAndView reject(@ModelAttribute("checkRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                         @RequestParam(value="policyCode", required=true) String policyCode,
                         Model model) {

        repaymentPolicyService.changeStatus(policyCode,"Rejected");
        repaymentPolicyService.updateAuthorizationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/rejectedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
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