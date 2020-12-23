package com.nucleus.eligibiltyparameter.aspect;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
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
public class EligibilityParameterLoggerAspect {
    Logger logger = LoggerFactory.getLogger(EligibilityParameterLoggerAspect.class);
    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.insertParameter(..))")
    public void insertParameterMethodDAO() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.getAll(..))")
    public void getAllParametersMethodDAO() {
    }


    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.deleteEligibilityParameter(..))")
    public void deleteParameterMethodDAO() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.editParameter(..))")
    public void editParameterMethodDAO() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.updateStatus(..))")
    public void updateStatusMethodDAO() {
    }


    @Before("insertParameterMethodDAO()")
    public void beforeInsertingParameterDAO() {
        logger.info("Adding Eligibility Parameter");
    }

    @AfterReturning(pointcut = "insertParameterMethodDAO()", returning = "success")
    public void afterInsertingParameterDAO(Object success) {
        boolean valid=(boolean)success;
        if(valid)
            logger.info("Added Eligibility Parameter Successfully");
        else
            logger.error("Error in adding eligibility parameter");
    }

    @Before("getAllParametersMethodDAO()")
    public void beforeGettingParametersDAO() {
        logger.info("Fetching All Eligibility Parameters");
    }


    @AfterReturning("getAllParametersMethodDAO()")
    public void afterGettingParametersDAO() {
        logger.info("Fetched All Eligibility Parameters");
    }


    @Before("deleteParameterMethodDAO()")
    public void beforeDeletingParameterDAO() {
        logger.info("Deleting Eligibility Parameter");
    }


    @AfterReturning(pointcut = "deleteParameterMethodDAO()", returning = "success")
    public void afterDeletingParameterDAO(Object success) {
        boolean valid=(boolean)success;
        if(valid)
            logger.info("Deleted Eligibility Parameter Successfully");
        else
            logger.error("Error in deleting eligibility parameter");
    }

    @Before("editParameterMethodDAO()")
    public void beforeEditingParameterDAO() {
        logger.info("Editing Eligibility Parameter");
    }


    @AfterReturning(pointcut = "editParameterMethodDAO()",returning = "success")
    public void afterEditingParameterDAO(Object success) {
        boolean valid=(boolean)success;
        if(valid)
            logger.info("Edited Eligibility Parameter Successfully");
        else
            logger.error("Error in editing eligibility parameter");
    }

    @Before("updateStatusMethodDAO()")
    public void beforeUpdatingStatusDAO() {
        logger.info("Updating Status");
    }


    @AfterReturning(pointcut = "updateStatusMethodDAO()",returning = "success")
    public void afterUpdatingStatusDAO(Object success) {
        boolean valid=(boolean)success;
        if(valid)
            logger.info("Updated Eligibility Parameter Successfully");
        else
            logger.error("Error in updating eligibility parameter");
    }


}
