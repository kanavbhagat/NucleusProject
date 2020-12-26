package com.nucleus.login.aspect;

import com.nucleus.repaymentpolicy.aspect.RepaymentPolicyLoggerAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * LoginLoggerAspect class acts as a logger for security based events in the application.
 *
 * @author  Manish Kumar Shukla
 * @version 1.0
 * @since   2020-12-25
 */
@Component
@Aspect
public class LoginLoggerAspect {

    Logger LOGGER = LoggerFactory.getLogger(LoginLoggerAspect.class);

    @After("execution(* com.nucleus.login.controller.*.loginPageSuccess(..))")
    public void loginSuccess() {
        LOGGER.info("********************************************************************");
        LOGGER.info("User Logged In!");
        LOGGER.info("********************************************************************");
    }

    @After("execution(* com.nucleus.login.controller.*.logoutPage(..))")
    public void logoutSuccess() {
        LOGGER.info("********************************************************************");
        LOGGER.info("User Logged Out!");
        LOGGER.info("********************************************************************");
    }

    @After("execution(* com.nucleus.login.controller.*.accessDeniedPage(..))")
    public void accessDenied(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        LOGGER.info("********************************************************************");
        LOGGER.info("XXXX Unauthorized Page was accessed XXXX. METHOD : "+ methodName + " CLASS : "+className);
        LOGGER.info("********************************************************************");
    }
}
