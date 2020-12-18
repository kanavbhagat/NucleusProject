package com.nucleus.eligibilitypolicy.service;

import com.nucleus.eligibilitypolicy.database.EligibilityPolicyDAO;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
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
    public boolean updateStatus(String policyCode, String action) {
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "APPROVED";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "REJECTED";
        } else
            newStatus = "PENDING";
        eligibilityPolicy.setStatus(newStatus);
        return eligibilityPolicyDAO.updateEligibilityPolicy(eligibilityPolicy);
    }

    @Override
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        EligibilityPolicy oldEligibilityPolicy = eligibilityPolicyDAO.getOneEligibilityPolicy(eligibilityPolicy.getPolicyCode());
        oldEligibilityPolicy.setPolicyName(eligibilityPolicy.getPolicyName());
        oldEligibilityPolicy.setPolicyDescription(eligibilityPolicy.getPolicyDescription());
        oldEligibilityPolicy.setEligibilityParameterList(eligibilityPolicy.getEligibilityParameterList());
        oldEligibilityPolicy.setStatus(eligibilityPolicy.getStatus());
        return eligibilityPolicyDAO.updateEligibilityPolicy(eligibilityPolicy);
    }

    @Override
    public boolean deleteEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.deleteEligibilityPolicy(policyCode);
    }

    @Override
    public EligibilityParameter getOneParameterFromName(String parameterName) {
        return eligibilityPolicyDAO.getOneParameterFromName(parameterName);
    }

}
