package com.nucleus.repaymentpolicy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RepaymentPolicyLoggerAspect {

    Logger LOGGER = LoggerFactory.getLogger(RepaymentPolicyLoggerAspect.class);

    @After("execution (* com.nucleus.repaymentpolicy.controller.RepaymentPolicyController.add(..))")
    public void login(JoinPoint joinPoint)
    {
        LOGGER.info("New Repayment Policy Added");
    }
}