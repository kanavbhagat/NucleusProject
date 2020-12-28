package com.nucleus.customer.aspect;

import  com.nucleus.customer.model.*;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.loanapplications.model.LoanApplications;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Aspect
public class CustomerLoggerAspect {

    private Logger logger = LoggerFactory.getLogger(Customer.class);
    String separator = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*";

    @Pointcut("execution(* com.nucleus.customer.dao.CustomerDaoInterface.addCustomer(..))")
    public void addCustomer(){}

    @Pointcut("execution(* com.nucleus.customer.dao.CustomerDaoInterface.updateCustomer(..))")
    public void updateCustomer(){}

    @Pointcut("execution(* com.nucleus.customer.dao.CustomerDaoInterface.listCustomer(..))")
    public void listCustomer(){}

    @Pointcut("execution(* com.nucleus.customer.dao.CustomerDaoInterface.removeCustomer(..))")
    public void removeCustomer(){}

    @Pointcut("execution(* com.nucleus.customer.dao.CustomerDaoInterface.getCustomerById(..))")
    public void getCustomerById(){}

    @Pointcut("execution(* com.nucleus.customer.dao.CustomerDaoInterface.getCustomerLoanDetails(..))")
    public void getCustomerLoanDetails(){}


    @Pointcut("execution(* com.nucleus.customer.dao.AddressDaoInterface.insertAddress(..))")
    public void insertAddress(){}






    @Before("addCustomer()")
    public void beforeCreatingCharge(JoinPoint joinPoint) {
        logger.info("Creating Customer... ");
        for(Object obj: joinPoint.getArgs()) {
            logger.info(String.valueOf(obj));
        }
    }
    @AfterReturning(pointcut = "addCustomer()", returning = "status")
    public void afterCreatingCharge(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Customer Created Successfully!");
        } else {
            logger.info("Failed to create new Customer!");
        }
        logger.info(separator);
    }



    @Before("updateCustomer()")
    public void beforeUpdatingEligibilityPolicy(JoinPoint joinPoint) {
        String customerCode = null;
        for(Object obj : joinPoint.getArgs()) {
            if(obj instanceof Customer) {
                customerCode = ((Customer) obj).getCustomerCode();
            }
        }
        logger.info("Saving Edits to Customer application : "+customerCode+" ...");
    }
    @AfterReturning(pointcut = "updateCustomer()", returning = "status")
    public void afterUpdatingEligibilityPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully saved edits to Customer application!");
        } else {
            logger.info("Failed to save edits of Customer application!");
        }
        logger.info(separator);
    }



    @Before("listCustomer()")
    public void beforeGettingAllPolicies(JoinPoint joinPoint) {
        logger.info("Fetching All Customer Applications... ");
    }
    @AfterReturning(pointcut = "listCustomer()", returning = "allCustomers")
    public void afterGettingAllPolicies(JoinPoint joinPoint, List<Customer> allCustomers) {
        int length = 0;
        if(allCustomers!=null) {
            length = allCustomers.size();
            logger.info(length + " Customer applications Fetched!");
        }

        logger.info(separator);
    }




    @Before("removeCustomer()")
    public void beforeDeletingEligibilityPolicy(JoinPoint joinPoint) {
        String customerCode = (String)joinPoint.getArgs()[0];
        logger.info("Deleting Customer: "+customerCode+" ...");
    }
    @AfterReturning(pointcut = "removeCustomer()", returning = "status")
    public void afterDeletingEligibilityPolicy(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Successfully deleted Customer!");
        } else {
            logger.info("Failed to delete Customer!");
        }
        logger.info(separator);
    }



    @Before("getCustomerById()")
    public void beforeGettingCustomerById(JoinPoint joinPoint) {
        logger.info("Getting customer's details... ");
    }
    @AfterReturning(pointcut = "getCustomerById()", returning = "customer")
    public void afterRequestingApprovalDAO(JoinPoint joinPoint, Customer customer) {
        logger.info("Customer fetched : "+customer);
        logger.info(separator);
    }



    @Before("getCustomerLoanDetails()")
    public void beforeGettingOnePolicy(JoinPoint joinPoint) {
        logger.info("Getting customer's Loan details... ");
    }
    @AfterReturning(pointcut = "getCustomerLoanDetails()", returning = "loanApplications")
    public void afterRequestingApprovalDAO(JoinPoint joinPoint, LoanApplications loanApplications) {
        logger.info("Loan application fetched : "+loanApplications);
        logger.info(separator);
    }




    @Before("insertAddress()")
    public void beforeInsertAddress(JoinPoint joinPoint) {
        logger.info("Inserting Address... ");
        for(Object obj: joinPoint.getArgs()) {
            logger.info(String.valueOf(obj));
        }
    }
    @AfterReturning(pointcut = "insertAddress()", returning = "status")
    public void afterInsertAddress(JoinPoint joinPoint, boolean status) {
        if(status) {
            logger.info("Address inserted Successfully!");
        } else {
            logger.info("Failed to insert address!");
        }
        logger.info(separator);
    }
}
