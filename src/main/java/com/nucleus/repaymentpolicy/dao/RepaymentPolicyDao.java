package com.nucleus.repaymentpolicy.dao;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;

import java.util.List;

public interface RepaymentPolicyDao {

    public List<RepaymentPolicy> getRepaymentPolicyList() ;
    public RepaymentPolicy getRepaymentPolicyById(String id) ;
    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy);
    public boolean deleteRepaymentPolicy(String id);
    public boolean updateRepaymentPolicy(RepaymentPolicy repaymentPolicy);
}
