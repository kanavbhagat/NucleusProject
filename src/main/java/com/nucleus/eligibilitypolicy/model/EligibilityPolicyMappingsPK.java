package com.nucleus.eligibilitypolicy.model;

import java.io.Serializable;
import java.util.Objects;

public class EligibilityPolicyMappingsPK implements Serializable {

    private String policyCode;

    private String parameterCode;

    // Getters - Setters
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EligibilityPolicyMappingsPK that = (EligibilityPolicyMappingsPK) o;
        return policyCode.equals(that.policyCode) &&
                parameterCode.equals(that.parameterCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyCode, parameterCode);
    }
}
