package com.nucleus.eligibilitypolicy.service;

import com.nucleus.eligibilitypolicy.database.EligibilityPolicyDAO;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * This class acts as a Service layer for all
 * Eligibility Policy related operations.
 *
 */
@Service
public class EligibilityPolicyServiceImpl implements EligibilityPolicyService{

    @Autowired
    EligibilityPolicyDAO eligibilityPolicyDAO;

    /**
     * This method is used to get a list of all Eligibility Policies.
     *
     * @return List This returns a list of all policies in the database.
     */
    @Override
    public List<EligibilityPolicy> getAllEligibilityPolicies() {
        return eligibilityPolicyDAO.getAllEligibilityPolicies();
    }

    /**
     * This method is used to add a new Eligibility Policy to database.
     *
     * @param eligibilityPolicy This is the model that has to be added to the database.
     *
     * @return boolean This returns a true/false based on whether the object was successfully added or not.
     */
    @Override
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        return eligibilityPolicyDAO.insertEligibilityPolicy(eligibilityPolicy);
    }

    /**
     * This method is used to retrieve one Eligibility Policy by Policy Code.
     *
     * @param policyCode This contains the policyCode
     *                   for which Eligibility Policy is to be fetched.
     *
     * @return EligibilityPolicy This returns the EligibilityPolicy that was required.
     */
    @Override
    public EligibilityPolicy getOneEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
    }

    /**
     * This method is used to update status {Approve/Reject} of Eligibility Policy.
     *
     * @param policyCode This contains the policyCode
     *                   for which status is to be changed.
     * @param action This holds one of two actions,
     *               (i.e. Approve/Reject), that decide the status.
     * @param authorizedBy This the username of the person who Approved/Rejected the policy.
     *
     * @return boolean This returns a true/false based on whether the status was successfully changed or not.
     */
    @Override
    public boolean updateStatus(String policyCode, String action, String authorizedBy) {
        EligibilityPolicy eligibilityPolicy = eligibilityPolicyDAO.getOneEligibilityPolicy(policyCode);
        String newStatus;
        if(action.equalsIgnoreCase("approve")) {
            newStatus = "Approved";
        } else if (action.equalsIgnoreCase("reject")) {
            newStatus = "Rejected";
        } else {
            newStatus = "Pending";
        }
        eligibilityPolicy.setStatus(newStatus);
        eligibilityPolicy.setAuthorizedBy(authorizedBy);
        eligibilityPolicy.setAuthorizedDate(LocalDate.now());
        return eligibilityPolicyDAO.updateEligibilityPolicy(eligibilityPolicy);
    }

    /**
     * This method is used to update an existing Eligibility Policy (all fields).
     *
     * @param eligibilityPolicy This is the new Eligibility Policy
     *                          that has to be inserted in place of the old one.
     *
     * @return boolean This returns a true/false based on whether the policy was successfully updated or not.
     */
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

    /**
     * This method is used to delete an existing Eligibility Policy.
     *
     * @param policyCode This contains the policyCode of the
     *                   Eligibility Policy that is to be deleted.
     *
     * @return boolean This returns a true/false based on whether the policy was successfully deleted or not.
     */
    @Override
    public boolean deleteEligibilityPolicy(String policyCode) {
        return eligibilityPolicyDAO.deleteEligibilityPolicy(policyCode);
    }

}
