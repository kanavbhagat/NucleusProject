package com.nucleus.loanapplications.controller;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.repaymentschedule.service.RepaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The LoanApplicationViewController class manipulates loan application
 * by calling the required method of the Loan Application Service Class with the
 * help of an object of LoanApplicationViewController Class. It also defines the getter
 * and setter methods for the mentioned object.
 */

@RestController
public class LoanApplicationViewController {

    @Autowired
    private RepaymentScheduleService repaymentScheduleService;


    @Autowired
    LoginDetailsImpl loginDetails;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class , new DateEditor());
    }
    @Autowired
    LoanApplicationService loanApplicationService;


    /**
     * This method which is authorized and then is triggered. It calls the
     * loanApplicationView method of Service class with the help of its object
     * declared above.
     *
     * @return modelAndView This returns a view of loanInformation form.
     */
    @PreAuthorize("hasRole ('ROLE_CHECKER') or hasRole('ROLE_MAKER')")
    @GetMapping(value="/loanApplication")
    public ModelAndView loanApplicationView(){
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanApplications");
        modelAndView.addObject("loanApplications",loanApplicationService.getAllLoanApplicationsList());
        return modelAndView;

    }

    /**
     * This method is used to delete a Loan application (entered by the user) from the database.
     *
     * @param loanApplicationNumber This contains loan Application ID whose application is to be deleted.
     *
     * @param model This is the modelAttribute received from the form.
     *
     * @return ModelAndView This returns a view with a Success or Error page.
     */
    @PreAuthorize("hasRole ('ROLE_MAKER')")
    @GetMapping(value="/loanApplication/delete")
    public ModelAndView deleteLoanApplication(@RequestParam(value="loanApplicationNumber", required=true) String loanApplicationNumber,
                                              Model model){

        loanApplicationService.deleteLoanApplicationId(Integer.parseInt(loanApplicationNumber));

        ModelAndView modelAndView =new ModelAndView("views/loanapplication/deletedpage");
        modelAndView.addObject("loanApplicationId",loanApplicationNumber);
        return modelAndView;
    }

    /**
     * This method is used to display editable details of existing Loan Application.
     *
     * @param loanApplicationNumber loan Application number which is going to be searched
     * @param model This model object is passed to the next pages.
     *
     * @return ModelAndView This returns path to a view for loan Information Maker.
     */
    @GetMapping(value = "loanApplication/edit")
    @PreAuthorize("hasRole ('ROLE_MAKER')")
    public ModelAndView editLoanApplication(@RequestParam(value = "loanApplicationNumber",required = true) String loanApplicationNumber, Model model){
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(Integer.parseInt(loanApplicationNumber));
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanInformationMaker");
        modelAndView.addObject("loanApplication",loanApplications);
        return modelAndView;
    }

    /**
     * This method is used to update editable details of existing Loan application.
     *
     * @param loanApplications loan Application number which is going to be searched
     *
     * @param model This model object is passed to the next pages.
     *
     * @param update This holds the update status
     *
     * @return ModelAndView The view containing edited page.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(params = {"op=Update"},value = "loanApplication/edit")
    public ModelAndView update(@ModelAttribute("loanApplication") LoanApplications loanApplications,@RequestParam("op") String update,
                               Model model){
        LoanApplications tempLoanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus(tempLoanApplications.getStatus());
        loanApplications.setAuthorizedDate(tempLoanApplications.getAuthorizedDate());
        loanApplications.setCustomerCode(tempLoanApplications.getCustomerCode());
        loanApplications.setCreateDate(tempLoanApplications.getCreateDate());
        loanApplications.setModifiedBy(loginDetails.getUserName());
        loanApplications.setModifiedDate(LocalDate.now());
        loanApplications.setCreatedBy(tempLoanApplications.getCreatedBy());
        loanApplications.setAuthorizedBy(tempLoanApplications.getAuthorizedBy());
        loanApplications.setLoanApplicationNumber(tempLoanApplications.getLoanApplicationNumber());

        loanApplicationService.updateLoanApplication(loanApplications);

        ModelAndView modelAndView = new ModelAndView("views/loanapplication/editedpage");
        modelAndView.addObject("loanApplicationId" , loanApplications.getLoanApplicationNumber());
        return modelAndView;
    }

    /**
     * This method is used to update editable details of existing Loan application and request.
     *
     * @param loanApplications loan Application number which is going to be searched
     *
     * @param model This model object is passed to the next pages.
     *
     * @param upAndreq This holds the update and request status
     *
     * @return ModelAndView This returns the view containing edited page and make request pending for approval.
     */
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(params = {"op=Update and Request"},value = "loanApplication/edit")
    public ModelAndView updateAndRequest(@ModelAttribute("loanApplication") LoanApplications loanApplications,@RequestParam("op") String upAndreq,
                               Model model){
        LoanApplications tempLoanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus(tempLoanApplications.getStatus());
        loanApplications.setAuthorizedDate(tempLoanApplications.getAuthorizedDate());
        loanApplications.setCustomerCode(tempLoanApplications.getCustomerCode());
        loanApplications.setCreateDate(tempLoanApplications.getCreateDate());
        loanApplications.setModifiedBy(loginDetails.getUserName());
        loanApplications.setModifiedDate(LocalDate.now());
        loanApplications.setCreatedBy(tempLoanApplications.getCreatedBy());
        loanApplications.setAuthorizedBy(tempLoanApplications.getAuthorizedBy());
        loanApplications.setLoanApplicationNumber(tempLoanApplications.getLoanApplicationNumber());
        loanApplications.setStatus("PENDING");
        loanApplicationService.updateLoanApplication(loanApplications);
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/editedpage");
        modelAndView.addObject("loanApplicationId" , loanApplications.getLoanApplicationNumber());

        return modelAndView;
    }





    /**
     * This method is used to update editable details of existing Loan application and request.
     *
     * @param loanApplicationNumber loan Application number which is going to be searched
     *
     * @param model This model object is passed to the next pages
     *
     * @return ModelAndView This returns the view of loanApplication Checker with required attributes.
     */
    @PreAuthorize("hasRole ('ROLE_CHECKER')")
    @GetMapping(value = "loanApplication/check")
    public ModelAndView getCheckUrl(@RequestParam(value = "loanApplicationNumber",required = true) String loanApplicationNumber, Model model){
        LoanApplications loanApplications = loanApplicationService.getLoanApplicationId(Integer.parseInt(loanApplicationNumber));

        ModelAndView modelAndView = new ModelAndView("views/loanapplication/loanApplicationChecker");
        modelAndView.addObject("loanApplicationNumber",loanApplicationNumber);
        modelAndView.addObject("loanApplication",loanApplications);
        return modelAndView;
    }





    /**
     * This method is used to update editable details of existing Loan application and request.
     *
     * @param loanApplications loan Application number which is going to be searched
     *
     * @param model This model object is passed to the next pages
     *
     * @param approve
     *
     * @return ModelAndView This returns the view containing approved page.
     */

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @PostMapping(params = {"op=approve"},value = "loanApplication/check")
    public ModelAndView approve(@ModelAttribute("loanApplication") LoanApplications loanApplications,
                               @RequestParam("op") String approve,
                                Model model){

        loanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus("APPROVED");
        loanApplications.setAuthorizedBy(loginDetails.getUserName());
        loanApplications.setAuthorizedDate(LocalDate.now());
        loanApplicationService.updateLoanApplication(loanApplications);
        /**
         * INTEGRATION with Repayment Schedule.
         * On execution of this function , Repayment Schedule would be generated and stored in the database.
         */
        repaymentScheduleService.addRepaymentSchedule(loanApplications);
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/approvedpage");
        modelAndView.addObject("loanApplicationId" , loanApplications.getLoanApplicationNumber());

        return modelAndView;

    }






    /**
     * This method is used to update editable details of existing Loan application and request.
     *
     * @param loanApplications loan Application number which is going to be searched
     *
     * @param reject
     *
     * @param model This model object is passed to the next pages
     *
     * @return ModelAndView This returns the view containing rejected page.
     */
    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @PostMapping(params = {"op=reject"},value = "loanApplication/check")
    public ModelAndView reject(@ModelAttribute("loanApplication") LoanApplications loanApplications,@RequestParam("op") String reject,
                                Model model){
        loanApplications = loanApplicationService.getLoanApplicationId(loanApplications.getLoanApplicationNumber());
        loanApplications.setStatus("REJECTED");
        loanApplications.setAuthorizedBy(loginDetails.getUserName());
        loanApplications.setAgreementDate(LocalDate.now());
        loanApplicationService.updateLoanApplication(loanApplications);
        ModelAndView modelAndView = new ModelAndView("views/loanapplication/rejectedpage");
        modelAndView.addObject("loanApplicationId" , loanApplications.getLoanApplicationNumber());

        return modelAndView;

    }


}
