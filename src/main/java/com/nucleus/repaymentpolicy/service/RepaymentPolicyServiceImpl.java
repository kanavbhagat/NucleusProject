package com.nucleus.repaymentpolicy.service;
import com.nucleus.repaymentpolicy.dao.RepaymentPolicyDaoImpl;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This class acts as a Service layer for all RepaymentPolicy related operations.
 *
 */
@Service
@Transactional
public class RepaymentPolicyServiceImpl implements RepaymentPolicyService {

    @Autowired
    private RepaymentPolicyDaoImpl repaymentPolicyDaoImpl;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    /**
     * Retrieves all the Repayment Policies existing in the database.
     * @return List of all repayment policies in the database.
     */
    @Override
    public List<RepaymentPolicy> getRepaymentPolicyList() {
        return repaymentPolicyDaoImpl.getRepaymentPolicyList();
    }

    /**
     * Retrieves a Repayment Policy by its policyCode from the database.
     * @param id is the policyCode String of the Repayment Policy to be retrieved.
     * @return object of given Repayment Policy if exists, else null.
     */
    @Override
    public RepaymentPolicy getRepaymentPolicyById(String id) {
        return repaymentPolicyDaoImpl.getRepaymentPolicyById(id);
    }

    /**
     * Adds a Repayment Policy to the database.
     * @param repaymentPolicy is the RepaymentPolicy to be saved.
     * @return Boolean true if creation was successful, else false.
     */
    @Override
    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy){
        boolean policyCode = repaymentPolicyDaoImpl.addRepaymentPolicy(repaymentPolicy);
        return policyCode;
    }

    /**
     * Deletes a Repayment Policy from the database.
     * @param id is the policyCode String of the RepaymentPolicy to be deleted.
     * @return Boolean true if update was successful, else false.
     */
    @Override
    public boolean deleteRepaymentPolicy(String id) {
        return repaymentPolicyDaoImpl.deleteRepaymentPolicy(id);
    }

    /**
     * Updates a Repayment Policy in the database.
     * @param repaymentPolicy repaymentPolicy is the RepaymentPolicy which will replace the existing one with same policyCode.
     * @return Boolean true if update was successful, else false.
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean updateRepaymentPolicy(RepaymentPolicy repaymentPolicy) {
        return repaymentPolicyDaoImpl.updateRepaymentPolicy(repaymentPolicy);
    }

    /**
     * Changes the status of a RepaymentPolicy. (SAVED, PENDING, APPROVED, REJECTED)
     * @param policyCode is policyCode String of RepaymentPolicy to which the status change is applied.
     * @param status is the new status String given to the chosen RepaymentPolicy.
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean changeStatus(String policyCode, String status) {
        return repaymentPolicyDaoImpl.changeStatus( policyCode,status);
    }

    /**
     * Adds the name of a RepaymentPolicy Creator and the date of Creation.
     * @param policyCode is policyCode String of RepaymentPolicy to which the Creation parameters are added.
     * @param name is the String of Creator name.
     * @return Boolean true if update was successful, else false.
     */
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateCreationParameters(String policyCode, String name) {
        return repaymentPolicyDaoImpl.updateCreationParameters(policyCode, name);
    }

    /**
     * Updates the name of a RepaymentPolicy Modifier and the date of Modification.
     * @param policyCode is policyCode String of RepaymentPolicy of which the Modification parameters are updated.
     * @param name is the String of Modifier name.
     * @return Boolean true if update was successful, else false.
     */
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateModificationParameters(String policyCode, String name) {
        return repaymentPolicyDaoImpl.updateModificationParameters(policyCode, name);
    }

    /**
     * Updates the name of a RepaymentPolicy Authorizer and the date of Authorization.
     * @param policyCode is policyCode String of RepaymentPolicy of which the Authorization parameters are updated.
     * @param name is the String of Authorizer name.
     * @return Boolean true if update was successful, else false.
     */
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateAuthorizationParameters(String policyCode, String name) {
        return repaymentPolicyDaoImpl.updateAuthorizationParameters(policyCode, name);
    }
}
