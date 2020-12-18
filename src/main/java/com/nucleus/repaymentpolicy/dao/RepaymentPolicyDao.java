package com.nucleus.repaymentpolicy.dao;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;

import java.util.List;

public interface RepaymentPolicyDao {
    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy);
    public boolean deleteRepaymentPolicy(String repaymentPolicyId);
    public boolean updateRepaymentPolicy(String repaymentPolicyId);
    public boolean getRepaymentPolicyById(String repaymentPolicyId);
    public List<RepaymentPolicy> getRepaymentPolicyList();
}
