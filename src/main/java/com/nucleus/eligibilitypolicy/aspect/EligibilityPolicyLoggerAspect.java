package com.nucleus.eligibilitypolicy.aspect;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class maintains logs for all
 * Eligibility Policy related operations.
 *
 */
@Component
@Aspect
public class EligibilityPolicyLoggerAspect {

    Logger logger = LoggerFactory.getLogger(EligibilityPolicyLoggerAspect.class);

    @Pointcut("execution(* com.nucleus.eligibilitypolicy.service.*.getAllEligibilityPolicies(..))")
    public void getAllPolicies() {
    }
    @Pointcut("execution(* com.nucleus.eligibilitypolicy.service.*.insertEligibilityPolicy(..))")
    public void insertEligibilityPolicy() {
    }
    @Pointcut("execution(* com.nucleus.eligibilitypolicy.service.*.getOneEligibilityPolicy(..))")
    public void getOneEligibilityPolicy() {
    }
    @Pointcut("execution(* com.nucleus.eligibilitypolicy.service.*.updateStatus(..))")
    public void updateStatus() {
    }
    @Pointcut("execution(* com.nucleus.eligibilitypolicy.service.*.updateEligibilityPolicy(..))")
    public void updateEligibilityPolicy() {
    }
    @Pointcut("execution(* com.nucleus.eligibilitypolicy.service.*.deleteEligibilityPolicy(..))")
    public void deleteEligibilityPolicy() {
    }


    @Before("getAllPolicies()")
    public void beforeGettingAllPolicies(JoinPoint joinPoint) {
        logger.info("Fetching All Eligibility Policies... ");
    }
    @AfterReturning(pointcut = "getAllPolicies()", returning = "allPolicies")
    public void afterGettingAllPolicies(JoinPoint joinPoint, List<EligibilityPolicy> allPolicies) {
        int length = 0;
        if(allPolicies!=null) {
            length = allPolicies.size();
        }
        logger.info(length + " Eligibility Policies Fetched!");
    }


    @Before("insertEligibilityPolicy()")
    public void beforeInsertingPolicy(JoinPoint joinPoint) {
        logger.info("Creating Eligibility Policy... ");
        for(Object obj: joinPoint.getArgs()) {
            logger.info(String.valueOf(obj));
        }
    }
    @AfterReturning(pointcut = "insertEligibilityPolicy()", returning = "status")
    public void afterInsertingPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully Created Eligibility Policy!");
        } else {
            logger.info("Failed to create new eligibility policy!");
        }
    }


    @Before("getOneEligibilityPolicy()")
    public void beforeGettingOnePolicy(JoinPoint joinPoint) {
        logger.info("Getting one eligibility policy... ");
    }
    @AfterReturning(pointcut = "getOneEligibilityPolicy()", returning = "eligibilityPolicy")
    public void afterRequestingApprovalDAO(JoinPoint joinPoint, EligibilityPolicy eligibilityPolicy) {
        logger.info("Eligibility Policy fetched : "+eligibilityPolicy);
    }


    @Before("updateStatus()")
    public void beforeUpdatingPolicyStatus(JoinPoint joinPoint) {
        String policyCode = (String) joinPoint.getArgs()[0];
        logger.info("Updating Status for Eligibility Policy: "+policyCode+" ...");
    }
    @AfterReturning(pointcut = "updateStatus()", returning = "status")
    public void afterUpdatingPolicyStatus(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully Updated Eligibility Policy Status!");
        } else {
            logger.info("Failed to update status of eligibility policy!");
        }
    }


    @Before("updateEligibilityPolicy()")
    public void beforeUpdatingEligibilityPolicy(JoinPoint joinPoint) {
        String policyCode = null;
        for(Object obj : joinPoint.getArgs()) {
            if(obj instanceof EligibilityPolicy) {
                policyCode = ((EligibilityPolicy) obj).getPolicyCode();
            }
        }
        logger.info("Saving Edits to Eligibility Policy: "+policyCode+" ...");
    }
    @AfterReturning(pointcut = "updateEligibilityPolicy()", returning = "status")
    public void afterUpdatingEligibilityPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully saved edits to Eligibility Policy!");
        } else {
            logger.info("Failed to save edits of eligibility policy!");
        }
    }


    @Before("deleteEligibilityPolicy()")
    public void beforeDeletingEligibilityPolicy(JoinPoint joinPoint) {
        String policyCode = (String)joinPoint.getArgs()[0];
        logger.info("Deleting Eligibility Policy: "+policyCode+" ...");
    }
    @AfterReturning(pointcut = "deleteEligibilityPolicy()", returning = "status")
    public void afterDeletingEligibilityPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully deleted Eligibility Policy!");
        } else {
            logger.info("Failed to delete eligibility policy!");
        }
    }
}