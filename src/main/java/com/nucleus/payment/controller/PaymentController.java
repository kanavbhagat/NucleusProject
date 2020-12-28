package com.nucleus.payment.controller;

import com.nucleus.loanapplications.service.LoanApplicationService;
import com.nucleus.payment.model.Payment;
import com.nucleus.payment.service.PaymentServiceImpl;
import com.nucleus.payment.service.DateEditor;
import com.nucleus.payment.validator.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "payment")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @Autowired
    LoanApplicationService loanApplicationService;

    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value("${status.approved}")
    private String approved;

    @Value("${status.created}")
    private String created;

    @Value("${status.deleted}")
    private String deleted;

    @Value("${status.edit}")
    private String edited;

//    @Autowired
//    PaymentValidator paymentValidator;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
//        dataBinder.setValidator(paymentValidator);
        dataBinder.registerCustomEditor(LocalDate.class, new DateEditor());
    }

    public PaymentServiceImpl getPaymentService(){
        return paymentService;
    }

    public void setPaymentService(PaymentServiceImpl paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping(value = {"/", ""})
    public ModelAndView viewPayments(){
        List<Payment> paymentList = paymentService.getAllPayments();
        ModelAndView modelAndView = new ModelAndView("views/payment/checkPayment");
        modelAndView.addObject("paymentList", paymentList);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/newPayment")
    public ModelAndView newPayment(){
        ModelAndView modelAndView = new ModelAndView("views/payment/newPayment");
        modelAndView.addObject("payment", new Payment());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/add")
    public ModelAndView addPayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("views/payment/newPayment");
            return modelAndView;
        }

        if(loanApplicationService.getLoanApplicationId(payment.getLoanApplicationNumber()) ==  null){
            ModelAndView modelAndView = new ModelAndView("views/payment/newPayment").
                    addObject("nullLoanID", "There does not exist Loan Application with this ID");
            return modelAndView;
        }
        else{
            if (!loanApplicationService.getLoanApplicationId(payment.getLoanApplicationNumber()).getCustomerCode().getCustomerCode().equals(payment.getCustomerCode())){
                ModelAndView modelAndView = new ModelAndView("views/payment/newPayment").
                        addObject("nullCustCode", "There does not exist Customer with this ID");
                return modelAndView;
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        payment.setPaymentStatus(pending);
        payment.setMadeBy(getModifiedBy());
        modelAndView.addObject("loanID", payment.getLoanApplicationNumber());
        boolean insertStatus = paymentService.insertPayment(payment);
        if(insertStatus){
            modelAndView.addObject("status", created);
            modelAndView.setViewName("views/payment/paymentSuccess");
        }
        else{
            modelAndView.addObject("status", created);
            modelAndView.setViewName("views/payment/paymentFailure");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/deletePayment/{loanID}")
    public ModelAndView deletePayment(@PathVariable(value = "loanID") int loanID){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanID", loanID);
        boolean deleteStatus = paymentService.deletePayment(loanID);
        if(deleteStatus){
            modelAndView.addObject("status", deleted);
            modelAndView.setViewName("views/payment/paymentSuccess");
        }
        else{
            modelAndView.addObject("status", deleted);
            modelAndView.setViewName("views/payment/paymentFailure");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = "/showPayment/{loanID}")
    public ModelAndView showPaymentForSuggestion(@PathVariable(value = "loanID") int loanID){
        ModelAndView modelAndView = new ModelAndView("views/payment/approveRejectPayment");
        modelAndView.addObject("approveRejectThisPayment", paymentService.getPaymentByLoanID(loanID));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_CHECKER')")
    @GetMapping(value = "/approveRejectPayment/{loanID}")
    public ModelAndView submitApproveRejectPaymentRequest(@PathVariable(value = "loanID") int loanID,
                                                          @RequestParam("suggestion") String suggestion){
        System.out.println("submitApproveRejectPaymentRequest"+loanID+getModifiedBy()+suggestion);
        ModelAndView modelAndView = new ModelAndView("views/payment/paymentSuccess");
        modelAndView.addObject("loanID", loanID);
        if (suggestion.equals("approve")){
            modelAndView.addObject("status", approved);
            paymentService.approveRejectPayment(loanID, approved, getModifiedBy());
        }
        else{
            modelAndView.addObject("status", rejected);
            paymentService.approveRejectPayment(loanID, rejected, getModifiedBy());
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "/editPayment/{loanID}")
    public ModelAndView editPayment(@PathVariable(value = "loanID") int loanID){
        ModelAndView modelAndView = new ModelAndView("views/payment/editPayment");
        modelAndView.addObject("editThisPayment", paymentService.getPaymentByLoanID(loanID));
        return modelAndView;
    }
    @PreAuthorize("hasRole('ROLE_MAKER')")
    @GetMapping(value = "viewPayment/{loanID}")
    public ModelAndView viewPayment(@PathVariable(value = "loanID") int loanID){
        ModelAndView modelAndView = new ModelAndView("views/payment/viewPayment");
        modelAndView.addObject("viewThisPayment", paymentService.getPaymentByLoanID(loanID));
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MAKER')")
    @PostMapping(value = "/editPayment/edit")
    public ModelAndView submitEditedPayment(@Valid @ModelAttribute("editThisPayment")Payment payment, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            ModelAndView modelAndView = new ModelAndView("views/payment/newPayment");
            return modelAndView;
        }
        System.out.println(payment.getLoanApplicationNumber());
        System.out.println("Edited Payment");
        System.out.println(payment.getCustomerCode());
        payment.setPaymentStatus(pending);
        payment.setMadeBy(getModifiedBy());
        boolean updateState = paymentService.updatePayment(payment);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanID", payment.getLoanApplicationNumber());
        if(updateState){
            modelAndView.addObject("status", edited);
            modelAndView.setViewName("views/payment/paymentSuccess");
        }
        else{
            modelAndView.addObject("status", edited);
            modelAndView.setViewName("views/payment/paymentFailure");
        }
        return modelAndView;
    }

    private String getModifiedBy(){
        String user;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user = ((UserDetails)principal).getUsername();
        } else {
            user = principal.toString();
        }
        return user;
    }

}
