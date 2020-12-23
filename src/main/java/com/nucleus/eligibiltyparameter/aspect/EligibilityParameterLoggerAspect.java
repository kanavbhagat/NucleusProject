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

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.insertParameterAndRequestApproval(..))")
    public void requestApprovalMethodDAO() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.database.EligibilityParameterDAO.getOneEligibilityParameter(..))")
    public void getOneParameterMethodDAO() {
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

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.insertParameter(..))")
    public void insertParameterMethodService() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.getAll(..))")
    public void getAllParametersMethodService() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.insertParameterAndRequestApproval(..))")
    public void requestApprovalMethodService() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.getOneEligibilityParameter(..))")
    public void getOneParameterMethodService() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.deleteEligibilityParameter(..))")
    public void deleteParameterMethodService() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.editParameter(..))")
    public void editParameterMethodService() {
    }

    @Pointcut("execution(* com.nucleus.eligibiltyparameter.service.EligibilityParameterService.updateStatus(..))")
    public void updateStatusMethodService() {
    }

    @Before("insertParameterMethodDAO()")
    public void beforeInsertingParameterDAO() {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(DATABASE): ");
    }

    @AfterReturning(pointcut = "insertParameterMethodDAO()", returning = "parameterCode")
    public void afterInsertingParameterDAO(Object parameterCode) {
        logger.info("(@AFTER RETURNING)Added Eligibility Parameter with Parameter Code(DATABASE): " + parameterCode);
    }

    @Before("getAllParametersMethodDAO()")
    public void beforeGettingParametersDAO() {
        logger.info("(@BEFORE)Getting All Eligibility Parameters(DATABASE): ");
    }


    @AfterReturning("getAllParametersMethodDAO()")
    public void afterGettingParametersDAO() {
        logger.info("(@AFTER RETURNING)Got All Eligibility Parameters(DATABASE): ");
    }

    @Before("requestApprovalMethodDAO()")
    public void beforeRequestingApprovalDAO() {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(DATABASE): ");
    }


    @AfterReturning(pointcut = "requestApprovalMethodDAO()", returning = "parameterCode")
    public void afterRequestingApprovalDAO(Object parameterCode) {
        logger.info("(@AFTER RETURNING)Inserted Eligibility Parameter and requested for approval with Parameter Code : (DATABASE): " + parameterCode);
    }

    @Before("getOneParameterMethodDAO()")
    public void beforeGettingParameterDAO() {
        logger.info("(@BEFORE)Getting Eligibility Parameter(DATABASE): ");
    }


//    @AfterReturning(pointcut = "getOneParameterMethodDAO()", returning = "eligibilityParameter")
//    public void afterGettingParameterDAO(Object eligibilityParameter) {
//        EligibilityParameter ep=(EligibilityParameter)eligibilityParameter;
//        logger.info("(@AFTER RETURNING)Got Eligibility Parameter with Parameter Code : (DATABASE): " + ep.getParameterCode());
//    }

    @Before("deleteParameterMethodDAO()")
    public void beforeDeletingParameterDAO() {
        logger.info("(@BEFORE)Deleting Eligibility Parameter(DATABASE): ");
    }


    @AfterReturning(pointcut = "deleteParameterMethodDAO()", returning = "parameterCode")
    public void afterDeletingParameterDAO(Object parameterCode) {
        logger.info("(@AFTER RETURNING)Deleted Eligibility Parameter with Parameter Code : (DATABASE): " + parameterCode);
    }

    @Before("editParameterMethodDAO()")
    public void beforeEditingParameterDAO() {
        logger.info("(@BEFORE)Editing Eligibility Parameter(DATABASE): ");
    }


    @AfterReturning(pointcut = "editParameterMethodDAO()")
    public void afterEditingParameterDAO() {
        logger.info("(@AFTER RETURNING)Edited Eligibility Parameter Successfully (DATABASE): ");
    }

    @Before("updateStatusMethodDAO()")
    public void beforeUpdatingStatusDAO() {
        logger.info("(@BEFORE)Updating Status(DATABASE): ");
    }


    @AfterReturning(pointcut = "updateStatusMethodDAO()")
    public void afterUpdatingStatusDAO() {
        logger.info("(@AFTER RETURNING)Updated Status Successfully (DATABASE): ");
    }

    @Before("insertParameterMethodService()")
    public void beforeInsertingParameterService() {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(SERVICE): ");
    }

    @AfterReturning(pointcut = "insertParameterMethodService()", returning = "parameterCode")
    public void afterInsertingParameterService(Object parameterCode) {
        logger.info("(@AFTER RETURNING)Added Eligibility Parameter with Parameter Code(SERVICE): " + parameterCode);
    }

    @Before("getAllParametersMethodService()")
    public void beforeGettingParametersService() {
        logger.info("(@BEFORE)Getting All Eligibility Parameters(SERVICE): ");
    }


//    @AfterReturning("getAllParametersMethodService()")
//    public void afterGettingParametersService() {
//        logger.info("(@AFTER RETURNING)Got All Eligibility Parameters(SERVICE): ");
//    }

    @Before("requestApprovalMethodService()")
    public void beforeRequestingApprovalService() {
        logger.info("(@BEFORE)Inserting Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "requestApprovalMethodService()", returning = "parameterCode")
    public void afterRequestingApprovalService(Object parameterCode) {
        logger.info("(@AFTER RETURNING)Inserted Eligibility Parameter and requested for approval with Parameter Code : (SERVICE): " + parameterCode);
    }

    @Before("getOneParameterMethodService()")
    public void beforeGettingParameterService() {
        logger.info("(@BEFORE)Getting Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "getOneParameterMethodService()", returning = "eligibilityParameter")
    public void afterGettingParameterService(Object eligibilityParameter) {
        EligibilityParameter ep=(EligibilityParameter)eligibilityParameter;
        logger.info("(@AFTER RETURNING)Got Eligibility Parameter with Parameter Code : (SERVICE): " + ep.getParameterCode());
    }

    @Before("deleteParameterMethodService()")
    public void beforeDeletingParameterService() {
        logger.info("(@BEFORE)Deleting Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "deleteParameterMethodService()", returning = "parameterCode")
    public void afterDeletingParameterService(Object parameterCode) {
        logger.info("(@AFTER RETURNING)Deleted Eligibility Parameter with Parameter Code : (SERVICE): " + parameterCode);
    }

    @Before("editParameterMethodService()")
    public void beforeEditingParameterService() {
        logger.info("(@BEFORE)Editing Eligibility Parameter(SERVICE): ");
    }


    @AfterReturning(pointcut = "editParameterMethodService()")
    public void afterEditingParameterService() {
        logger.info("(@AFTER RETURNING)Edited Eligibility Parameter Successfully (SERVICE): ");
    }

    @Before("updateStatusMethodService()")
    public void beforeUpdatingStatusService() {
        logger.info("(@BEFORE)Updating Status(SERVICE): ");
    }


    @AfterReturning(pointcut = "updateStatusMethodService()")
    public void afterUpdatingStatusService() {
        logger.info("(@AFTER RETURNING)Updated Status Successfully (SERVICE): ");
    }
}
