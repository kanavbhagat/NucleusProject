package com.nucleus.eligibilitypolicy.service;

import com.nucleus.eligibilitypolicy.database.EligibilityPolicyDAO;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    //To get a list of all Eligibility Policies:
    @Override
    public List<EligibilityPolicy> getAllEligibilityPolicies() {
        return eligibilityPolicyDAO.getAllEligibilityPolicies();
    }

    //To add a new Eligibility Policy to database:
    @Override
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        return eligibilityPolicyDAO.insertEligibilityPolicy(eligibilityPolicy);
    }

    //To retrieve one Eligibility Policy by Policy Code:
    @Override
    public EligibilityPolicy getOneEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
    }

    //To update status {Approve/Reject} Eligibility Policy:
    @Override
    public boolean updateStatus(String policyCode, String action, String authorizedBy) {
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "APPROVED";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "REJECTED";
        } else
            newStatus = "PENDING";
        eligibilityPolicy.setStatus(newStatus);
        eligibilityPolicy.setAuthorizedBy(authorizedBy);
        eligibilityPolicy.setAuthorizedDate(LocalDate.now());
        return eligibilityPolicyDAO.updateEligibilityPolicy(eligibilityPolicy);
    }

    //To update an existing Eligibility Policy (all fields):
    @Override
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        EligibilityPolicy oldEligibilityPolicy = eligibilityPolicyDAO.getOneEligibilityPolicy(eligibilityPolicy.getPolicyCode());
        oldEligibilityPolicy.setPolicyName(eligibilityPolicy.getPolicyName());
        oldEligibilityPolicy.setPolicyDescription(eligibilityPolicy.getPolicyDescription());
        oldEligibilityPolicy.setEligibilityParameterList(eligibilityPolicy.getEligibilityParameterList());
        oldEligibilityPolicy.setStatus(eligibilityPolicy.getStatus());
        oldEligibilityPolicy.setModifiedBy(eligibilityPolicy.getModifiedBy());
        oldEligibilityPolicy.setModifiedDate(eligibilityPolicy.getModifiedDate());
        oldEligibilityPolicy.setAuthorizedBy(null);
        oldEligibilityPolicy.setAuthorizedDate(null);
        return eligibilityPolicyDAO.updateEligibilityPolicy(oldEligibilityPolicy);
    }

    //To delete an existing Eligibility Policy:
    @Override
    public boolean deleteEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.deleteEligibilityPolicy(policyCode);
    }

}
