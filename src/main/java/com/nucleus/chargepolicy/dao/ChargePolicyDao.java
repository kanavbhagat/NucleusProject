package com.nucleus.chargepolicy.dao;

import com.nucleus.chargepolicy.model.ChargePolicy;

import java.util.List;




public interface ChargePolicyDao {
    public void insert(ChargePolicy chargePolicy);
    public List<ChargePolicy> getPolicyList();
    public ChargePolicy getChargePolicy(String chargePolicyCode);
    void updateEntry(ChargePolicy chargePolicy);
    void updateStatus(String chargePolicyCode,String status);
    void deleteChargePolicy(String chargePolicyCode);
}
