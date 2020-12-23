package com.nucleus.payment.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PaymentAspect {
    Logger log = LoggerFactory.getLogger(PaymentAspect.class);

    @Before("execution (* com.nucleus.product.service.ProductService.*.*(..))")
    public void logBefore(JoinPoint joinPoint){
        log.info(joinPoint.getSignature().getName()+" is being executed");
    }

}
