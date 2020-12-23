package com.nucleus.customerservice.loandisbursal.aspect;

import com.nucleus.loanapplications.model.LoanApplications;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class maintains logs for all
 * Loan Disbursal related operations.
 *
 */
@Component
@Aspect
public class LoanDisbursalLoggerAspect {

    Logger logger = LoggerFactory.getLogger(LoanDisbursalLoggerAspect.class);

    @Pointcut("execution(* com.nucleus.customerservice.loandisbursal.service.*.getLoanDetails(..))")
    public void getLoanDetails() {
    }
    @Pointcut("execution(* com.nucleus.customerservice.loandisbursal.service.*.getCustomerLoanDetails(..))")
    public void getCustomerLoanDetails() {
    }

    @Before("getLoanDetails()")
    public void beforeLoanDetails(JoinPoint joinPoint) {
        logger.info("{} Fetching Loan Disbursal Details... ",joinPoint);
    }
    @AfterReturning(pointcut = "getLoanDetails()", returning = "loanApp")
    public void afterLoanDetails(JoinPoint joinPoint, LoanApplications loanApp) {
        logger.info("Loan Details Fetched!");
    }

    @Before("getCustomerLoanDetails()")
    public void beforeAllLoans(JoinPoint joinPoint) {
        logger.info("{} Fetching Loan Disbursal Details... ",joinPoint);
    }
    @AfterReturning(pointcut = "getCustomerLoanDetails()", returning = "loanApps")
    public void afterAllLoans(JoinPoint joinPoint, List<LoanApplications> loanApps) {
        int size = 0;
        if(loanApps!=null) {
            size = loanApps.size();
        }
        logger.info(size+" Loan Details Fetched by Customer");
    }
}
