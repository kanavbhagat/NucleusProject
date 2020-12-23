package com.nucleus.chargepolicy.service;

import com.nucleus.charge.dao.ChargeDao;
import com.nucleus.charge.model.NewCharge;
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

    @Autowired
    private ChargeDao chargeDao;

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


    public List<ChargePolicy> getPolicyList(){
        System.out.println("In Service");

        return  this.chargePolicyDao.getPolicyList();
    }
    public ChargePolicy getChargePolicy(String chargePolicyCode){
        ChargePolicy chargePolicy = this.chargePolicyDao.getChargePolicy(chargePolicyCode);

        return chargePolicy;
    }
    public boolean updateStatus(String chargePolicyCode,String status,String approvedBy){
        return this.chargePolicyDao.updateStatus(chargePolicyCode,status,approvedBy);
    }
    public boolean updateEntry(ChargePolicy chargePolicy, String chargePolicyCode){
        return this.chargePolicyDao.updateEntry(chargePolicy);
    }
    public void deleteChargePolicy(String chargePolicyCode){
        this.chargePolicyDao.deleteChargePolicy(chargePolicyCode);
    }
    public List<String> getChargeCodesList(){
        List<NewCharge> listofNewCharges = this.chargeDao.getChargeList();
        List<String> chargeCodeList = new ArrayList<String>();
        for(NewCharge newCharge : listofNewCharges){
            chargeCodeList.add(newCharge.getChargeCode());
        }
        return  chargeCodeList;
    }
    public String getChargeCodeName(String chargeCode){
        List<NewCharge> listofNewCharges = this.chargeDao.getChargeList();
        if(listofNewCharges==null)return  null;
        String chargeCodeName = "";
        for(NewCharge newCharge : listofNewCharges){
            System.out.println(" Charge Code traversing " + newCharge.getChargeCode());
            if(newCharge.getChargeCode().equals(chargeCode)){
                chargeCodeName = newCharge.getChargeCodeName();
                System.out.println(" Charge Code Name " + chargeCodeName);
                break;
            }
        }
        return  chargeCodeName;
    }


}
