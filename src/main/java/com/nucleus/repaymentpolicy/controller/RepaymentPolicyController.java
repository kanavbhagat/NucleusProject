package com.nucleus.repaymentpolicy.controller;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import com.nucleus.repaymentpolicy.service.RepaymentPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * RepaymentPolicyController class acts as a Controller layer for all RepaymentPolicy related operations.
 *
 * @author  Gyanesh Anand , Rahul Tehlan
 * @version 1.0
 * @since   2020-12-25
 */
@Controller
@PropertySource("classpath:status.properties")
public class RepaymentPolicyController {

    @Autowired
    RepaymentPolicyService repaymentPolicyService;

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
     * Handles and retrieves all RepaymentPolicies and show them in a JSP page allRepaymentPoliciesData.
     *
     * @return ModelAndView returns the view containing allRepaymentPoliciesData.jsp
     */
    @RequestMapping(value = "/showRepaymentPolicy", method = RequestMethod.GET)
    public ModelAndView getNewRepaymentPolicies() {
        List<RepaymentPolicy> newRepaymentPolicies = repaymentPolicyService.getRepaymentPolicyList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/allRepaymentPoliciesData");
        mv.addObject("newRepaymentPolicies", newRepaymentPolicies);
        return mv;
    }

    /**
     * Receives the request of Adding New Repayment Policy and retrieves corresponding JSP page.
     * This method is accessible only by the Maker.
     *
     * @return the view containing newRepaymentPolicyScreenMaker.jsp
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(value = "/showRepaymentPolicy/add", method = RequestMethod.GET)
    public ModelAndView getAdd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
        mv.addObject("newRepaymentPolicy", new RepaymentPolicy());
        return mv;
    }


    /**
     * Saves a new Repayment Policy by delegating the processing to RepaymentPolicyService.
     * Since the policy is saved, the status is updated to SAVED.
     * This method is accessible only by the Maker.
     *
     * @param repaymentPolicy is the new RepaymentPolicy to be added to the database if Valid.
     * @param result is the Binding result from the page for validation.
     *
     * @return the view containing one of the below JSP pages
     *
     * Reloads same newRepaymentPolicyScreenMaker JSP page if the BindingResult has errors.
     * Displays a confirmation on addedpage JSP page if successful.
     * Displays a failure on RPAddErrorPage JSP page if failure due to constraint violations.
     */


    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="save",value = "/showRepaymentPolicy/add", method = RequestMethod.POST)
    public ModelAndView add(@Valid RepaymentPolicy repaymentPolicy, BindingResult result) {
        if(result.hasErrors())
        {
            ModelAndView mv = new ModelAndView();
            mv.addObject("newRepaymentPolicy", new RepaymentPolicy());
            mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
            return mv;
        }

        boolean successFlag = repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
        if(successFlag)
        {
            repaymentPolicyService.changeStatus(repaymentPolicy.getPolicyCode(),saved);
            repaymentPolicyService.updateCreationParameters(repaymentPolicy.getPolicyCode(),getPrincipal());
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/addedpage");
            mv.addObject("policyCode", repaymentPolicy.getPolicyCode());
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/RPAddErrorPage");
            return mv;
        }
    }

    /**
     * Saves and Request Approval for a new Repayment Policy by delegating the processing to RepaymentPolicyService.
     * Since the policy is saved and approval requested, the status is updated to PENDING.
     * This method is accessible only by the Maker.
     *
     * @param repaymentPolicy is the new RepaymentPolicy to be added to the database if Valid.
     * @param result is the Binding result from the page for validation.
     *
     * @return the view containing one of the below JSP page.
     * Reloads same newRepaymentPolicyScreenMaker JSP page if the BindingResult has errors.
     * Displays a confirmation on addedpage JSP page if successful.
     * Displays a failure on RPAddErrorPage JSP page if failure due to constraint violations.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="saveApprove",value = "/showRepaymentPolicy/add", method = RequestMethod.POST)
    public ModelAndView addAndSendForApproval(@Valid RepaymentPolicy repaymentPolicy, BindingResult result) {
        if(result.hasErrors())
        {
            ModelAndView mv = new ModelAndView();
            mv.addObject("newRepaymentPolicy", new RepaymentPolicy());
            mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenMaker");
            return mv;
        }

        boolean successFlag = repaymentPolicyService.addRepaymentPolicy(repaymentPolicy);
        if(successFlag)
        {
            repaymentPolicyService.changeStatus(repaymentPolicy.getPolicyCode(),pending);
            repaymentPolicyService.updateCreationParameters(repaymentPolicy.getPolicyCode(),getPrincipal());
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/addedpage");
            mv.addObject("policyCode", repaymentPolicy.getPolicyCode());
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/RPAddErrorPage");
            return mv;
        }
    }

    /**
     * Deletes an existing Repayment Policy by delegating the processing to RepaymentPolicyService.
     * This method is accessible only by the Maker.
     *
     * @param policyCode is the policyCode of RepaymentPolicy to be deleted from the database.
     *
     * @return the view containing the below deletedpage.jsp page
     */

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(value = "/showRepaymentPolicy/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value="policyCode", required=true) String policyCode) {
        repaymentPolicyService.deleteRepaymentPolicy(policyCode);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/deletedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    /**
     * Receives the request of Editing an existing Repayment Policy and retrieves corresponding JSP page.
     * This method is accessible only by the Maker.
     *
     * @param policyCode Policy code to be searched.
     * @return the view containing editpage.jsp with attributes from existing newRepaymentPolicyAttribute.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(value = "/showRepaymentPolicy/edit", method = RequestMethod.GET)
    public ModelAndView getEdit(@RequestParam(value="policyCode", required=true) String policyCode) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/editpage");
        mv.addObject("newRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        return mv;
    }

    /**
     * Updates an existing Repayment Policy by delegating the processing to RepaymentPolicyService.
     * Since the policy is just updated, the status is updated to SAVED.
     * This method is accessible only by the Maker.
     *
     * @param repaymentPolicy is the new Repayment Policy with updated parameters.
     * @param policyCode is the policyCode of older Repayment Policy to be updated.
     *
     * @return the view containing editedpage.jsp
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="update",value = "/showRepaymentPolicy/edit", method = RequestMethod.POST)
    public ModelAndView saveEdit(@ModelAttribute("newRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                           @RequestParam(value="policyCode", required=true) String policyCode) {

        repaymentPolicy.setPolicyCode(policyCode);
        boolean successFlag = repaymentPolicyService.updateRepaymentPolicy(repaymentPolicy);
        if(successFlag)
        {
            repaymentPolicyService.changeStatus(policyCode,saved);
            repaymentPolicyService.updateModificationParameters(policyCode,getPrincipal());
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/editedpage");
            mv.addObject("policyCode", policyCode);
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/RPEditErrorPage");
            return mv;
        }
    }

    /**
     * Updates an existing Repayment Policy by delegating the processing to RepaymentPolicyService.
     * Since the policy is updated and Approval requested, the status is updated to PENDING.
     * This method is accessible only by the Maker.
     *
     * @param repaymentPolicy is the new Repayment Policy with updated parameters.
     * @param policyCode is the policyCode of older Repayment Policy to be updated.
     *
     * @return the view containing editedpage.jsp
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @RequestMapping(params ="updateApprove",value = "/showRepaymentPolicy/edit", method = RequestMethod.POST)
    public ModelAndView saveApproveEdit(@ModelAttribute("newRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                                  @RequestParam(value="policyCode", required=true) String policyCode) {

        repaymentPolicy.setPolicyCode(policyCode);
        boolean successFlag = repaymentPolicyService.updateRepaymentPolicy(repaymentPolicy);
        if(successFlag)
        {
            repaymentPolicyService.changeStatus(policyCode,pending);
            repaymentPolicyService.updateModificationParameters(policyCode,getPrincipal());
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/editedpage");
            mv.addObject("policyCode", policyCode);
            return mv;
        }
        else
        {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("views/repaymentpolicy/RPEditErrorPage");
            return mv;
        }
    }

    /**
     * Receives the request of Checking an existing Repayment Policy and retrieves corresponding JSP page.
     * This method is accessible only by the Checker.
     *
     * @param policyCode POlicy Code to be searched
     * @return the view containing newRepaymentPolicyScreenChecker.jsp with attributes from existing newRepaymentPolicyAttribute.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @RequestMapping(value = "/showRepaymentPolicy/check", method = RequestMethod.GET)
    public ModelAndView getCheck(@RequestParam(value="policyCode", required=true) String policyCode) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/newRepaymentPolicyScreenChecker");
        mv.addObject("checkRepaymentPolicyAttribute", repaymentPolicyService.getRepaymentPolicyById(policyCode));
        return mv;
    }

    /**
     * Approves an existing Repayment Policy by delegating the processing to RepaymentPolicyService.
     * Since the policy is Approved, the status is updated to APPROVED.
     * This method is accessible only by the Checker.
     *
     * @param repaymentPolicy
     * @param policyCode is the policyCode of the Repayment Policy to be approved.
     *
     * @return the view containing approvedpage.jsp
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @RequestMapping(params ="approve" ,value = "/showRepaymentPolicy/check", method = RequestMethod.POST)
    public ModelAndView approve(@ModelAttribute("checkRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                          @RequestParam(value="policyCode", required=true) String policyCode) {

        repaymentPolicyService.changeStatus(policyCode,approved);
        repaymentPolicyService.updateAuthorizationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/approvedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    /**
     * Rejects an existing Repayment Policy by delegating the processing to RepaymentPolicyService.
     * Since the policy is Rejected, the status is updated to REJECTED.
     * This method is accessible only by the Checker.
     *
     * @param repaymentPolicy
     * @param policyCode is the policyCode of the Repayment Policy to be rejected.
     *
     * @return the view containing rejectedpage.jsp
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @RequestMapping(params ="reject" ,value = "/showRepaymentPolicy/check", method = RequestMethod.POST)
    public ModelAndView reject(@ModelAttribute("checkRepaymentPolicyAttribute") RepaymentPolicy repaymentPolicy,
                         @RequestParam(value="policyCode", required=true) String policyCode) {

        repaymentPolicyService.changeStatus(policyCode,rejected);
        repaymentPolicyService.updateAuthorizationParameters(policyCode,getPrincipal());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/repaymentpolicy/rejectedpage");
        mv.addObject("policyCode", policyCode);
        return mv;
    }

    /**
     * Used to retrive the Username of the user currently logged in.
     * @return username String of the User performing actions.
     */
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