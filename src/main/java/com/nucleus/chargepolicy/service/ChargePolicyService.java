package com.nucleus.chargepolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import spring.dao.ChargePolicyDao;
import com.nucleus.chargepolicy.dao.ChargePolicyDao;
import com.nucleus.chargepolicy.dao.ChargePolicySearchDao;
import com.nucleus.chargepolicy.model.ChargePolicy;

@Service
public class ChargePolicyService {
    @Autowired
    ChargePolicyDao chargePolicyDao;

    public void insert(ChargePolicy chargePolicy) {
        System.out.println("In Service");
        chargePolicyDao = new ChargePolicyDao();
        chargePolicyDao.insert(chargePolicy);
    }
}
