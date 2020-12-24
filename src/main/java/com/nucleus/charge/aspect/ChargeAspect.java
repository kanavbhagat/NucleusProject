package com.nucleus.charge.aspect;

import com.nucleus.charge.model.NewCharge;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * This class maintains logs for all
 * Charge related operations.
 *
 */
@Component
@Aspect
public class ChargeAspect {

    private Logger logger = LoggerFactory.getLogger(ChargeAspect.class);
    String separator = "*********************************";
    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.getChargeList(..))")
    public void getChargeList(){}

    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.getPendingChargeList(..))")
    public void pendingChargeList(){}

    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.insert(..))")
    public void createCharge(){}

    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.delete*(..))")
    public void deleteCharge(){}

    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.getOneCharge(..))")
    public void getSingleCharge(){}

    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.updateCharge(..))")
    public void editCharge(){}

    @Pointcut("execution(* com.nucleus.charge.dao.ChargeDao.updateStatus(..))")
    public void updateStatus(){}

    @Before("getChargeList()")
    public void beforeGettingAllCharges(JoinPoint joinPoint) {
        logger.info("Fetching All Charges... ");
    }
    @AfterReturning(pointcut = "getChargeList()", returning = "charges")
    public void afterGettingAllCharges(JoinPoint joinPoint, List<NewCharge> charges) {
        int length = 0;
        if(charges!=null) {
            length = charges.size();
        }
        logger.info(length + " Charges Fetched!");
        logger.info(separator);
    }

    @Before("pendingChargeList()")
    public void beforeGettingPendingCharges(JoinPoint joinPoint) {
        logger.info("Fetching Pending Charges... ");
    }
    @AfterReturning(pointcut = "pendingChargeList()", returning = "charges")
    public void afterGettingPendingCharges(JoinPoint joinPoint, List<NewCharge> charges) {
        int length = 0;
        if(charges!=null) {
            length = charges.size();
        }
        logger.info(length + " Pending Charges Fetched!");
        logger.info(separator);
    }

    @Before("createCharge()")
    public void beforeCreatingCharge(JoinPoint joinPoint) {
        logger.info("Creating Charge... ");
        for(Object obj: joinPoint.getArgs()) {
            logger.info(String.valueOf(obj));
        }
    }
    @AfterReturning(pointcut = "createCharge()", returning = "status")
    public void afterCreatingCharge(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Charge Created Successfully!");
        } else {
            logger.info("Failed to create new charge!");
        }
        logger.info(separator);
    }

    @Before("getSingleCharge()")
    public void beforeGettingOneCharge(JoinPoint joinPoint) {
        logger.info("Getting one charge... ");
    }
    @AfterReturning(pointcut = "getSingleCharge()", returning = "charge")
    public void afterGettingOneCharge(JoinPoint joinPoint, NewCharge charge) {
        logger.info("Charge fetched : "+ charge);
        logger.info(separator);
    }


    @Before("updateStatus()")
    public void beforeUpdatingChargeStatus(JoinPoint joinPoint) {
        String chargeCode = (String) joinPoint.getArgs()[0];
        logger.info("Updating Status for Charge: " + chargeCode + " ...");
    }

    @AfterReturning(pointcut = "updateStatus()", returning = "status")
    public void afterUpdatingChargeStatus(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Charge Status Updated Successfully!");
        } else {
            logger.info("Failed to update status of charge!");
        }
        logger.info(separator);
    }


    @Before("editCharge()")
    public void beforeEditingCharge(JoinPoint joinPoint) {
        String chargeCode = null;
        for(Object obj : joinPoint.getArgs()) {
            if(obj instanceof NewCharge) {
                chargeCode = ((NewCharge) obj).getChargeCode();
            }
        }
        logger.info("Saving Edits to Charge: "+ chargeCode +" ...");
    }

    @AfterReturning(pointcut = "editCharge()", returning = "status")
    public void afterEditingCharge(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Charge edits saved successfully!");
        } else {
            logger.info("Failed to save edits of charge!");
        }
        logger.info(separator);
    }

    @Before("deleteCharge()")
    public void beforeDeletingCharge(JoinPoint joinPoint) {
        String chargeCode = (String)joinPoint.getArgs()[0];
        logger.info("Deleting Eligibility Policy: "+ chargeCode +" ...");
    }

    @AfterReturning(pointcut = "deleteCharge()", returning = "status")
    public void afterDeletingCharge(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Charge deleted successfully!");
        } else {
            logger.info("Failed to delete charge!");
        }
        logger.info(separator);
    }

}
