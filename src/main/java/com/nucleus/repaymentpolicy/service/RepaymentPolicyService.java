package com.nucleus.repaymentpolicy.service;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;

import java.util.List;

public interface RepaymentPolicyService {

    public List<RepaymentPolicy> getRepaymentPolicyList() ;
    public RepaymentPolicy getRepaymentPolicyById(String id) ;
    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy);
    public boolean deleteRepaymentPolicy(String id);
    public boolean updateRepaymentPolicy(RepaymentPolicy repaymentPolicy);
    public boolean changeStatus(String policyCode, String status);
    public boolean updateCreationParameters(String policyCode, String name);
    public boolean updateModificationParameters(String policyCode,String name);
    public boolean updateAuthorizationParameters(String policyCode,String name);
}
