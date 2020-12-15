package com.nucleus.eligibilitypolicy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eligibility_policy_mappings")
public class EligibilityPolicyMappings {

    @Id
    @Column(name = "policy_code")
    private String policyCode;

    @Id
    @Column(name = "parameter_code")
    private String parameterCode;

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }
}
