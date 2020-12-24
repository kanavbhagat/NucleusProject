package com.nucleus.repaymentpolicy.aspect;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class RepaymentPolicyLoggerAspect {

    Logger LOGGER = LoggerFactory.getLogger(RepaymentPolicyLoggerAspect.class);

    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.getRepaymentPolicyList(..))")
    public void getRepaymentPolicyList() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.addRepaymentPolicy(..))")
    public void addRepaymentPolicy() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.getRepaymentPolicyById(..))")
    public void getRepaymentPolicyById() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.changeStatus(..))")
    public void changeStatus() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.updateRepaymentPolicy(..))")
    public void updateRepaymentPolicy() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.deleteRepaymentPolicy(..))")
    public void deleteRepaymentPolicy() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.updateCreationParameters(..))")
    public void updateCreationParameters() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.updateModificationParameters(..))")
    public void updateModificationParameters() {
    }
    @Pointcut("execution(* com.nucleus.repaymentpolicy.service.*.updateAuthorizationParameters(..))")
    public void updateAuthorizationParameters() {
    }



    @Before("getRepaymentPolicyList()")
    public void beforeGettingAllPolicies(JoinPoint joinPoint) {
        LOGGER.info("Fetching All Repayment Policies... ");
    }
    @AfterReturning(pointcut = "getRepaymentPolicyList()", returning = "allPolicies")
    public void afterGettingAllPolicies(JoinPoint joinPoint, List<RepaymentPolicy> allPolicies) {
        int length = 0;
        if(allPolicies!=null) {
            length = allPolicies.size();
        }
        LOGGER.info("********************************************************************");
        LOGGER.info(length + " Repayment Policies Fetched!");
        LOGGER.info("********************************************************************");
    }


    @Before("addRepaymentPolicy()")
    public void beforeAddingPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Creating Repayment Policy... ");
        for(Object obj: joinPoint.getArgs()) {
            LOGGER.info(String.valueOf(obj));
        }
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "addRepaymentPolicy()", returning = "status")
    public void afterInsertingPolicy(JoinPoint joinPoint, boolean status) {
        LOGGER.info("********************************************************************");
        if(status) {
            LOGGER.info("Successfully Created Repayment Policy!");
        } else {
            LOGGER.info("Failed to create new Repayment policy!");
        }
        LOGGER.info("********************************************************************");
    }


    @Before("getRepaymentPolicyById()")
    public void beforeGettingPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Getting Searched Repayment policy... ");
        LOGGER.info("********************************************************************");
    }

    @AfterReturning(pointcut = "getRepaymentPolicyById()", returning = "repaymentPolicy")
    public void afterGettingPolicy(JoinPoint joinPoint, RepaymentPolicy repaymentPolicy) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Repayment Policy fetched : "+repaymentPolicy);
        LOGGER.info("********************************************************************");
    }


    @Before("changeStatus()")
    public void beforeUpdatingPolicyStatus(JoinPoint joinPoint) {
        LOGGER.info("Updating Status for Repayment Policy");
    }
    @AfterReturning(pointcut = "changeStatus()")
    public void afterUpdatingPolicyStatus(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String status = (String) joinPoint.getArgs()[1];
        switch (status) {
            case "Saved":
                LOGGER.info("Successfully Updated Repayment Policy Status to " + status);
                break;
            case "Pending":
                LOGGER.info("Successfully Updated Repayment Policy Status to " + status);
                break;
            case "Approved":
                LOGGER.info("Successfully Updated Repayment Policy Status to " + status);
                break;
            case "Rejected":
                LOGGER.info("Successfully Updated Repayment Policy Status to " + status);
                break;
            default:
                LOGGER.info("Failed to update status of Repayment policy!");
                break;
        }
        LOGGER.info("********************************************************************");
    }


    @Before("updateRepaymentPolicy()")
    public void beforeUpdatingRepaymentPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Saving Edits to Repayment Policy");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateRepaymentPolicy()")
    public void afterUpdatingRepaymentPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Successfully saved edits to Repayment Policy!");
        LOGGER.info("********************************************************************");
    }


    @Before("deleteRepaymentPolicy()")
    public void beforeDeletingRepaymentPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Deleting Repayment Policy");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "deleteRepaymentPolicy()")
    public void afterDeletingRepaymentPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Successfully deleted Repayment Policy!");
        LOGGER.info("********************************************************************");
    }

    @Before("updateCreationParameters()")
    public void beforeUpdatingCreationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Adding Creator Name");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateCreationParameters()")
    public void afterUpdatingCreationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Successfully Added Creator Name!");
        LOGGER.info("********************************************************************");
    }

    @Before("updateModificationParameters()")
    public void beforeUpdatingModificationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Updating Modifier Name");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateModificationParameters()")
    public void afterUpdatingModificationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Successfully updated Modifier Name!");
        LOGGER.info("********************************************************************");
    }

    @Before("updateAuthorizationParameters()")
    public void beforeUpdatingAuthorizationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Updating Authorizer Name");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateAuthorizationParameters()")
    public void afterUpdatingAuthorizationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Successfully updated Authorizer Name!");
        LOGGER.info("********************************************************************");
    }



}