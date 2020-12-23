package com.nucleus.loanclosurebod.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoanClosureAspect {

    /**
     * This class maintains logs for all
     * Loan Closure BOD related operations.
     *
     */
    Logger logger = LoggerFactory.getLogger(LoanClosureAspect.class);

    @Pointcut("execution(* com.nucleus.loanclosurebod.service.*.loanClosureBod(..))")
    public void loanClosureBod(){
    }

    @Pointcut("execution(* com.nucleus.loanclosurebod.service.*.closeOneLoan(..))")
    public void closeOneLoan(){
    }

    @Before("loanClosureBod()")
    public void beforeloanClosureBod(JoinPoint joinPoint) {
        logger.info("Fetching All Loan Applications... ");
    }
    @AfterReturning(pointcut = "loanClosureBod()", returning = "countofClosedLoans")
    public void afterloanClosureBod(JoinPoint joinPoint, int countofClosedLoans) {
        logger.info(countofClosedLoans+ " Loans Closed!");
    }

    @Before("closeOneLoan()")
    public void beforecloseOneLoan(JoinPoint joinPoint) {
        logger.info("Fetching Repayment Schedule... ");
    }
    @AfterReturning(pointcut = "closeOneLoan()", returning = "closureStatus")
    public void aftercloseOneLoan(JoinPoint joinPoint, boolean closureStatus) {
        if(closureStatus){
            logger.info( "Loan Closed Successfully");
        }
        else{
            logger.info("Loan not eligible for Closure");
        }
    }
}
