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

    public void setChargePolicyDao(ChargePolicyDao chargePolicyDao) {
        this.chargePolicyDao = chargePolicyDao;
    }

   /* public void getChargePolicyList(ChargePolicy chargePolicy) {
        System.out.println("In Service");
        this.chargePolicyDao.getChargePolicyList();
    }*/



    public void setEligibilityPolicyDAO(ChargePolicyDao chargePolicyDao) {
        this.chargePolicyDao = chargePolicyDao;
    }
    public int insert(ChargePolicy chargePolicy) {
        return this.chargePolicyDao.insert(chargePolicy);
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
    public ChargePolicy getChargePolicy(String chargePolicyCode){
        ChargePolicy chargePolicy = this.chargePolicyDao.getChargePolicy(chargePolicyCode);
        return chargePolicy;
    }
    public void updateStatus(String chargePolicyCode,String status){
        this.chargePolicyDao.updateStatus(chargePolicyCode,status);
    }
    public void updateEntry(ChargePolicy chargePolicy, String chargePolicyCode){
        this.chargePolicyDao.updateEntry(chargePolicy);
    }
    public void deleteChargePolicy(String chargePolicyCode){
        this.chargePolicyDao.deleteChargePolicy(chargePolicyCode);
    }

}
