package com.nucleus.eligibilitypolicy.service;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;

import java.util.List;

public interface EligibilityPolicyService {

    public List<EligibilityPolicy> getAllEligibilityPolicies();
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy);
    public EligibilityPolicy getOneEligibilityPolicy(String policyCode);
    public boolean updateStatus(String policyCode, String newStatus);
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy);
    public boolean deleteEligibilityPolicy(String policyCode);

    EligibilityParameter getOneParameterFromName(String parameterName);
}
