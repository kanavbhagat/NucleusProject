package com.nucleus.chargepolicy.service;

import com.nucleus.chargepolicy.dao.ChargePolicyDao;
import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChargePolicyService {

    public List<String> getChargeCodes();
    public List<ChargePolicy> getPolicyList();
//    public void setEligibilityPolicyDAO(ChargePolicyDao chargePolicyDao);
    public void insert(ChargePolicy chargePolicy);
    public void getCharge(String code);

    ChargePolicy getChargePolicy(String chargePolicyCode);

    void updateStatus(String chargePolicyCode,String newStatus);

    void updateEntry(ChargePolicy chargePolicy, String chargePolicyCode);

    void deleteChargePolicy(String chargePolicyCode);
}
