package com.nucleus.repaymentpolicy.service;

import com.nucleus.repaymentpolicy.dao.RepaymentPolicyDaoImpl;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @Override
    public List<RepaymentPolicy> getRepaymentPolicyList() {
        return repaymentPolicyDaoImpl.getRepaymentPolicyList();
    }

    @Override
    public RepaymentPolicy getRepaymentPolicyById(String id) {
        return repaymentPolicyDaoImpl.getRepaymentPolicyById(id);
    }

    @Override
    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy){

        boolean policyCode = repaymentPolicyDaoImpl.addRepaymentPolicy(repaymentPolicy);
        return policyCode;
    }


    @Override
    public void deleteRepaymentPolicy(String id) {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            protected void doInTransactionWithoutResult(TransactionStatus status) {
                repaymentPolicyDaoImpl.deleteRepaymentPolicy(id);
            }
        });
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void updateRepaymentPolicy(RepaymentPolicy repaymentPolicy) {

        repaymentPolicyDaoImpl.updateRepaymentPolicy(repaymentPolicy);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void changeStatus(String policyCode, String status)
    {
        repaymentPolicyDaoImpl.changeStatus( policyCode,status);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void updateCreationParameters(String policyCode, String name) {
        repaymentPolicyDaoImpl.updateCreationParameters(policyCode, name);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void updateModificationParameters(String policyCode, String name) {
        repaymentPolicyDaoImpl.updateModificationParameters(policyCode, name);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void updateAuthorizationParameters(String policyCode, String name) {
        repaymentPolicyDaoImpl.updateAuthorizationParameters(policyCode, name);
    }
}
