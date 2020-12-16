package com.nucleus.eligibilitypolicy.service;

import com.nucleus.eligibilitypolicy.database.EligibilityPolicyDAO;
import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityPolicyServiceImpl implements EligibilityPolicyService{

    @Autowired
    EligibilityPolicyDAO eligibilityPolicyDAO;

    public EligibilityPolicyDAO getEligibilityPolicyDAO() {
        return eligibilityPolicyDAO;
    }

    public void setEligibilityPolicyDAO(EligibilityPolicyDAO eligibilityPolicyDAO) {
        this.eligibilityPolicyDAO = eligibilityPolicyDAO;
    }

    @Override
    public List<EligibilityPolicy> getAllEligibilityPolicies() {
        return eligibilityPolicyDAO.getAllEligibilityPolicies();
    }

    @Override
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        return eligibilityPolicyDAO.insertEligibilityPolicy(eligibilityPolicy);
    }

    @Override
    public EligibilityPolicy getOneEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
    }

    @Override
    public boolean updateStatus(String policyCode, String newStatus) {
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
        eligibilityPolicy.setStatus(newStatus);
        return eligibilityPolicyDAO.updateEligibilityPolicy(eligibilityPolicy);
    }

    @Override
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        return eligibilityPolicyDAO.updateEligibilityPolicy(eligibilityPolicy);
    }

    @Override
    public boolean deleteEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.deleteEligibilityPolicy(policyCode);
    }

    @Override
    public List<EligibilityParameter> getParameters() {
        return eligibilityPolicyDAO.getParameters();
    }

    @Override
    public void addParameters() {
        eligibilityPolicyDAO.addParameters();
    }
}
