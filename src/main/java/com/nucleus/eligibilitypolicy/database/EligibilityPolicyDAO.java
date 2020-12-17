package com.nucleus.eligibilitypolicy.database;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;

import java.util.List;

public interface EligibilityPolicyDAO {

    public List<EligibilityPolicy> getAllEligibilityPolicies();
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy);
    public EligibilityPolicy getOneEligibilityPolicy(String policyCode);
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy);
    public boolean deleteEligibilityPolicy(String policyCode);

    List<EligibilityParameter> getParameters();

    void addParameters();

}
