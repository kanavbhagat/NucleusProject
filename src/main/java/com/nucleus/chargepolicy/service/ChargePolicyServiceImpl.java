package com.nucleus.chargepolicy.service;

import com.nucleus.chargepolicy.dao.ChargePolicyDao;
import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChargePolicyServiceImpl implements ChargePolicyService {

    @Autowired
    private ChargePolicyDao chargePolicyDao;
    public ChargePolicyDao getChargePolicyDao() {
        return chargePolicyDao;
    }

    public void insert(ChargePolicy chargePolicy) {
        System.out.println("In Service");
        this.chargePolicyDao.insert(chargePolicy);
    }
    public List<String> getChargeCodes(){
        List<String> chargeCodes = new ArrayList<String>();
        chargeCodes.add("101");
        chargeCodes.add("102");
        chargeCodes.add("103");
        chargeCodes.add("104");
        return  chargeCodes;
    }

    public List<ChargePolicy> getPolicyList(){
        System.out.println("In Service");

        return  this.chargePolicyDao.getPolicyList();
    }
    public void getCharge(String code){
        System.out.println("In service getCharge with code " + code);
        this.chargePolicyDao.getChargePolicy(code);
    }


}
