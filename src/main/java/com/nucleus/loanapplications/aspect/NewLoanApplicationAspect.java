package com.nucleus.loanapplications.aspect;

import com.nucleus.loanapplications.model.LoanApplications;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Aspect
public class NewLoanApplicationAspect {
    private Logger logger = LoggerFactory.getLogger(LoanApplications.class);
    String separator = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*";

    @Pointcut("execution(* com.nucleus.loanapplications.dao.LoanApplicationDaoInterface.addApplication(..))")
    public void addApplication(){}

    @Pointcut("execution(* com.nucleus.loanapplications.dao.LoanApplicationDaoInterface.updateLoanApplication(..))")
    public void updateLoanApplication(){}

    @Pointcut("execution(* com.nucleus.loanapplications.dao.LoanApplicationDaoInterface.getLoanApplicationList(..))")
    public void getLoanApplicationList(){}

    @Pointcut("execution(* com.nucleus.loanapplications.dao.LoanApplicationDaoInterface.deleteLoanApplication(..))")
    public void deleteLoanApplication(){}

    @Pointcut("execution(* com.nucleus.loanapplications.dao.LoanApplicationDaoInterface.getLoanApplicationId(..))")
    public void getLoanApplicationId(){}








    @Before("addApplication()")
    public void beforeCreatingCharge(JoinPoint joinPoint) {
        logger.info("Creating Loan application... ");
        for(Object obj: joinPoint.getArgs()) {
            logger.info(String.valueOf(obj));
        }
    }
    @AfterReturning(pointcut = "addApplication()", returning = "status")
    public void afterCreatingCharge(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Loan application Created Successfully!");
        } else {
            logger.info("Failed to create new Loan application!");
        }
        logger.info(separator);
    }



    @Before("updateLoanApplication()")
    public void beforeUpdatingEligibilityPolicy(JoinPoint joinPoint) {
        Integer loanApplications = null;
        for(Object obj : joinPoint.getArgs()) {
            if(obj instanceof LoanApplications) {
                loanApplications = ((LoanApplications) obj).getLoanApplicationNumber();
            }
        }
        logger.info("Saving Edits to Loan application : "+loanApplications+" ...");
    }
    @AfterReturning(pointcut = "updateLoanApplication()", returning = "status")
    public void afterUpdatingEligibilityPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully saved edits to Loan application!");
        } else {
            logger.info("Failed to save edits of Loan application!");
        }
        logger.info(separator);
    }



    @Before("getLoanApplicationList()")
    public void beforeGettingAllPolicies(JoinPoint joinPoint) {
        logger.info("Fetching All Loan Applications... ");
    }
    @AfterReturning(pointcut = "getLoanApplicationList()", returning = "allLoans")
    public void afterGettingAllPolicies(JoinPoint joinPoint, List<LoanApplications> allLoans) {
        int length = 0;
        if(allLoans!=null) {
            length = allLoans.size();
            logger.info(length + " Loan applications Fetched!");
        }

        logger.info(separator);
    }




    @Before("deleteLoanApplication()")
    public void beforeDeletingEligibilityPolicy(JoinPoint joinPoint) {
        Integer loanid = (Integer) joinPoint.getArgs()[0];
        logger.info("Deleting Loan application (ID): "+loanid+" ...");
    }
    @AfterReturning(pointcut = "deleteLoanApplication()", returning = "status")
    public void afterDeletingEligibilityPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully deleted Loan application!");
        } else {
            logger.info("Failed to delete Loan application!");
        }
        logger.info(separator);
    }



    @Before("getLoanApplicationId()")
    public void beforeGettingCustomerById(JoinPoint joinPoint) {
        logger.info("Getting Loan details... ");
    }
    @AfterReturning(pointcut = "getLoanApplicationId()", returning = "loan")
    public void afterRequestingApprovalDAO(JoinPoint joinPoint, LoanApplications loan) {
        logger.info("Loan application fetched : "+loan);
    }




}
