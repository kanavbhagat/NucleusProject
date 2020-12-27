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

	@Before("p4()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("*****************");
		if((joinPoint.getArgs()[0])!= null)logger.info("Inserting charge policy with code " + ((ChargePolicy)joinPoint.getArgs()[0]).getChargePolicyCode());
		else logger.info("Null Arguments in insert method");
		logger.info("*********************");
	}
	@AfterReturning(value = "p4()" ,returning = "name")
	public void logAfterInsert(JoinPoint jp, Object name) {
		logger.info("********************");
		logger.info("Returning from insert");
		if((Integer)name == 1)logger.info("Insertion Successful");
		else if ((Integer)name == 2) logger.info("Insertion Unsuccessful. Please see error in the stack trace");
		else if ((Integer)name == 3) {logger.info("Duplicate primary key");
		}
		logger.info("**********");
	}
	@AfterReturning(value = "p1() || p2() || p6()" ,returning = "name")
	public void logAfter(JoinPoint jp, Object name) {
		logger.info("********************");
		logger.info("Method called " + jp.getStaticPart().toString());
		logger.info("-------------------");
		if(name instanceof ArrayList)logger.info("List of Size " + ((ArrayList<?>) name).size());
		else if (name instanceof ChargePolicy)logger.info("Charge Policy with code " + ((ChargePolicy) name).getChargePolicyName());
		else if (name instanceof Integer){
			if( (Integer)name == 1 ) logger.info(" Deleted a row ");
			else logger.info("No row available");
		}
		logger.info("*****************");
	}



}
