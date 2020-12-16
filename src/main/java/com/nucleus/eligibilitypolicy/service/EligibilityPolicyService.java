package com.nucleus.eligibilitypolicy.service;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;

import java.util.List;

public interface EligibilityPolicyService {

    public List<EligibilityPolicy> getAllEligibilityPolicies();
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy);
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy);
    public boolean deleteEligibilityPolicy(EligibilityPolicy eligibilityPolicy);

    List<EligibilityParameter> getParameters();
}
