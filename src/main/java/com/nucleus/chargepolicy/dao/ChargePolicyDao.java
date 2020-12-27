package com.nucleus.chargepolicy.dao;

import com.nucleus.chargepolicy.model.ChargePolicy;
import java.util.List;




public interface ChargePolicyDao {
    public int insert(ChargePolicy chargePolicy);
    public List<ChargePolicy> getPolicyList();
    public ChargePolicy getChargePolicy(String chargePolicyCode);
    boolean updateEntry(ChargePolicy chargePolicy);
    boolean updateStatus(String chargePolicyCode,String status,String approvedBy);
    int deleteChargePolicy(String chargePolicyCode);
}
