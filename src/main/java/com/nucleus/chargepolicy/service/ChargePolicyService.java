package com.nucleus.chargepolicy.service;


import com.nucleus.chargepolicy.model.ChargePolicy;
import java.util.List;

public interface ChargePolicyService {

    public List<ChargePolicy> getPolicyList();
    public int insert(ChargePolicy chargePolicy);
    ChargePolicy getChargePolicy(String chargePolicyCode);
    boolean updateStatus(String chargePolicyCode,String newStatus,String approvedBy);
    boolean updateEntry(ChargePolicy chargePolicy, String chargePolicyCode);
    void deleteChargePolicy(String chargePolicyCode);
    List<String> getChargeCodesList();
    public String getChargeCodeName(String chargeCode);
}
