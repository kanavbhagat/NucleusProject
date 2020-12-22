package com.nucleus.repaymentpolicy.service;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;

import java.util.List;

public interface RepaymentPolicyService {

    public List<RepaymentPolicy> getRepaymentPolicyList() ;
    public RepaymentPolicy getRepaymentPolicyById(String id) ;
    public String addRepaymentPolicy(RepaymentPolicy repaymentPolicy) ;
    public void deleteRepaymentPolicy(String id);
    public void updateRepaymentPolicy(RepaymentPolicy repaymentPolicy);
    public void changeStatus(String policyCode, String status);
    public void updateCreationParameters(String policyCode, String name);
    public void updateModificationParameters(String policyCode,String name);
    public void updateAuthorizationParameters(String policyCode,String name);
}
