package com.nucleus.chargepolicy.aspect;

import com.nucleus.chargepolicy.model.ChargePolicy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Aspect
@Order(1)
public class ChargePolicyAspect {

//@Before("execution(* com.example.model.Customer.add*(..))" )
	//@Before("within(com.example.model.*)")
	Logger logger = LoggerFactory.getLogger(ChargePolicyAspect.class);

	@Pointcut("execution(* com.nucleus.chargepolicy.dao.ChargePolicyDao.getPolicyList*(..))")
	public void p1(){}

	@Pointcut("execution(* com.nucleus.chargepolicy.dao.ChargePolicyDao.getChargePolicy*(..))")
	public void p2(){}

	@Pointcut("execution(* com.nucleus.chargepolicy.dao.ChargePolicyDao.updateEntry*(..))")
	public void p3(){}

	@Pointcut("execution(* com.nucleus.chargepolicy.dao.ChargePolicyDao.insert*(..))")
	public void p4(){}

	@Pointcut("execution(* com.nucleus.chargepolicy.dao.ChargePolicyDao.updateStatus*(..))")
	public void p5(){}

	@Pointcut("execution(* com.nucleus.chargepolicy.dao.ChargePolicyDao.deleteChargePolicy*(..))")
	public void p6(){}

	@Before(" p3() || p4() || p5() || p6()")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println(joinPoint.getStaticPart());
		System.out.println(joinPoint.getArgs().length);
		System.out.println("TestAspect is running\n**********************");

		}

	@AfterReturning(value = "p1() || p2() || p6()" ,returning = "name")
	public void logAfter(JoinPoint jp, Object name) {
		System.out.println("********************");
		System.out.println("After returning Advice Applied");
		if(name instanceof ArrayList)System.out.println("List of Size " + ((ArrayList<?>) name).size());
		else if (name instanceof ChargePolicy) System.out.println("Charge Policy with code " + ((ChargePolicy) name).getChargePolicyCode());
		else if (name instanceof Integer){
			if( (Integer)name == 1 ) System.out.println(" Deleted a row ");
			else System.out.println("No row available");
		}
		System.out.println("**********");
	}

	@AfterThrowing(pointcut="p4()" , throwing="exception")
	public void logAfterThrowing(JoinPoint jp, Exception exception) {
		System.out.println("After throwing Advice Applied " + exception.getMessage());


	}

}
