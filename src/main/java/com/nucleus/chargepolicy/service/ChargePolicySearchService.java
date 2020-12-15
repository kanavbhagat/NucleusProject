package com.nucleus.chargepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.nucleus.chargepolicy.dao.ChargePolicySearchDao;
import com.nucleus.chargepolicy.model.ChargePolicy;
import java.util.List;

public class ChargePolicySearchService {
    @Autowired
    ChargePolicySearchDao chargePolicySearchDao;
    public void insert(ChargePolicy chargePolicy){
        System.out.println("In Service");
        chargePolicySearchDao = new ChargePolicySearchDao();
        chargePolicySearchDao.getChargePolicyList();
    }
    public List<ChargePolicy> getChargePolicyList(){
        return new ChargePolicySearchDao().getChargePolicyList();
    }
}
