package com.nucleus.repaymentschedule.aspect;

import com.nucleus.repaymentschedule.model.RepaymentSchedule;
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
 * RepaymentScheduleLoggerAspect class acts as a Logger for Repayment Schedule related function.
 * Logging has been done on the basis of Spring AOP concepts.
 * Logger used - Logback and slf4j as interface.
 *
 * @author  Ritika Nagar
 * @version 1.0
 * @since   2020-12-25
 */

@Component
@Aspect
public class RepaymentScheduleLoggerAspect {
    Logger LOGGER = LoggerFactory.getLogger(RepaymentScheduleLoggerAspect.class);


    @Pointcut("execution(* com.nucleus.repaymentschedule.service.*.addRepaymentSchedule(..))")
    public void addRepaymentSchedule() {
    }
    @Pointcut("execution(* com.nucleus.customerservice.repaymentschedulereport.service.*.getRepaymentScheduleReport(..))")
    public void getRepaymentSchedule() {
    }


    @Before("getRepaymentSchedule()")
    public void beforeGettingRepaymentSchedule(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info(" Fetching Repayment Schedule... ");
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "getRepaymentSchedule()", returning = "rslist")
    public void afterGettingRepaymentSchedule(JoinPoint joinPoint, List<RepaymentSchedule> rslist) {
        double emi = 0;
        int loanApplicationNumber=0;
        if(rslist.size()!=0) {
           emi = rslist.get(0).getEmi();
           loanApplicationNumber = rslist.get(0).getLoanApplicationNumber().getLoanApplicationNumber();
            LOGGER.info("********************************************************************");
            LOGGER.info( "Repayment Schedule of Loan Application Number "+loanApplicationNumber +
                    " is fetched and its EMI amount is " + emi);
            LOGGER.info("********************************************************************");
        }
        else
        {
            LOGGER.info("********************************************************************");
            LOGGER.info( " No such Repayment Schedule exists with this Loan Application Number.");
            LOGGER.info("********************************************************************");
        }
    }


    @Before("addRepaymentSchedule()")
    public void beforeAddingRepaymentSchedule(JoinPoint joinPoint) {
        LOGGER.info("********************************************************************");
        LOGGER.info(" Creating Repayment Schedule... ");
        for(Object obj: joinPoint.getArgs()) {
            LOGGER.info(String.valueOf(obj));
        }
        LOGGER.info("********************************************************************");
    }
    @AfterReturning(pointcut = "addRepaymentSchedule()", returning = "status")
    public void afterInsertingPolicy(JoinPoint joinPoint, int status) {
        LOGGER.info("********************************************************************");
        if(status==1) {
            LOGGER.info(" Successfully Created Repayment Schedule! ");
        } else {
            LOGGER.info(" Failed to create new Repayment Schedule as Loan Application Number already exists. ");
        }
        LOGGER.info("********************************************************************");
    }

}
