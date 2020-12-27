package com.nucleus.repaymentpolicy.aspect;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RepaymentPolicyLoggerAspect class acts as a Logger for Repayment Policy related function.
 * Logging has been done on the basis of Spring AOP concepts.
 * Logger used - Logback and slf4j as interface.
 *
 * @author  Gyanesh Anand , Rahul Tehlan
 * @version 1.0
 * @since   2020-12-25
 */
@Component
@Aspect
public class RepaymentPolicyLoggerAspect {

    //Retuens an object of Logger for all the below logging functions.
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
        LOGGER.info("********************************************************************");
        LOGGER.info("Fetching All Repayment Policies... ");
        LOGGER.info("********************************************************************");
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
            LOGGER.info("Failed to create new Repayment policy as policyCode or name already exists.");
        }
        LOGGER.info("********************************************************************");
    }


    @Before("getRepaymentPolicyById()")
    public void beforeGettingPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String policyCode = (String) joinPoint.getArgs()[0];
        LOGGER.info("Opening Searched Repayment policy with id: "+policyCode+"\n");
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
        LOGGER.info("********************************************************************");
        LOGGER.info("Updating Status for Repayment Policy");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "changeStatus()")
    public void afterUpdatingPolicyStatus(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String status = (String) joinPoint.getArgs()[1];
        LOGGER.info("Successfully Updated Repayment Policy Status to " + status);
        LOGGER.info("********************************************************************");
    }

    @Before("updateRepaymentPolicy()")
    public void beforeUpdatingRepaymentPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info("Saving Edits to Repayment Policy");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateRepaymentPolicy()", returning = "status")
    public void afterUpdatingRepaymentPolicy(JoinPoint joinPoint, boolean status) {
        LOGGER.info("********************************************************************");
        if(status) {
            LOGGER.info("Successfully saved edits to Repayment Policy!");
        } else {
            LOGGER.info("Edits can't be made as Policy Name already exists.");
        }
        LOGGER.info("********************************************************************");
    }


    @Before("deleteRepaymentPolicy()")
    public void beforeDeletingRepaymentPolicy(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String policyCode = (String)joinPoint.getArgs()[0];
        LOGGER.info("Deleting Repayment Policy with policyCode: "+policyCode+"\n");
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
        String policyCode = (String)joinPoint.getArgs()[0];
        LOGGER.info("Adding Creator Name for policyCode: "+policyCode+"\n");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateCreationParameters()")
    public void afterUpdatingCreationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String name = (String)joinPoint.getArgs()[1];
        LOGGER.info("Successfully Added Creator Name as "+name+"\n");
        LOGGER.info("********************************************************************");
    }

    @Before("updateModificationParameters()")
    public void beforeUpdatingModificationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String policyCode = (String)joinPoint.getArgs()[0];
        LOGGER.info("Updating Modifier Name for policyCode: "+policyCode+"\n");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateModificationParameters()")
    public void afterUpdatingModificationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String name = (String)joinPoint.getArgs()[1];
        LOGGER.info("Successfully updated Modifier Name as "+name+"\n");
        LOGGER.info("********************************************************************");
    }

    @Before("updateAuthorizationParameters()")
    public void beforeUpdatingAuthorizationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String policyCode = (String)joinPoint.getArgs()[0];
        LOGGER.info("Updating Authorizer Name for policyCode: "+policyCode+"\n");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "updateAuthorizationParameters()")
    public void afterUpdatingAuthorizationParameters(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        String name = (String)joinPoint.getArgs()[1];
        LOGGER.info("Successfully updated Authorizer Name as "+name+"\n");
        LOGGER.info("********************************************************************");
    }



}