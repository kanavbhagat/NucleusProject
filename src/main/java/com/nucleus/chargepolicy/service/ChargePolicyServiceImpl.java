package com.nucleus.chargepolicy.service;

import com.nucleus.charge.dao.ChargeDao;
import com.nucleus.charge.model.NewCharge;
import com.nucleus.chargepolicy.dao.ChargePolicyDao;
import com.nucleus.chargepolicy.model.ChargePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a Service layer for all
 * Charge Policy related operations.
 *
 */
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

    /**
     * This method inserts the entry to the database
     * @param chargePolicy This is the model which is to be inserted in the database
     * @return It returns an integer which can take three values based on the status of insertion.
     * status - 1 : Successfully inserted to the database
     * status - 2 : Either the chargePolicy sent is null or any error apart from duplicate ID error.
     * status - 3 : Duplicate ID(Primary Key) which is chargePolicyCode in this case.
     */
    public int insert(ChargePolicy chargePolicy) {
        return this.chargePolicyDao.insert(chargePolicy);
    }

    /**
     * @return Returns the entire list of charge policies in the database.
     */
    public List<ChargePolicy> getPolicyList(){ return  this.chargePolicyDao.getPolicyList();}
    /**
     *
     * @param chargePolicyCode This is the charge policy code of the Charge Policy to be retrieved.
     * @return Returns Charge Policy corresponding to the policy code
     */
    public ChargePolicy getChargePolicy(String chargePolicyCode){return this.chargePolicyDao.getChargePolicy(chargePolicyCode); }
    /**
     * This method updates the status of the Charge Policy.
     * @param chargePolicyCode Policy Code of the policy whose status needs to be changed
     * @param status this is status of the charge policy currently as sent by the user
     * @param approvedBy name of the checker who approved this Charge Policy
     * @return true when update is successful, false otherwise.
     */
    public boolean updateStatus(String chargePolicyCode,String status,String approvedBy){
        return this.chargePolicyDao.updateStatus(chargePolicyCode,status,approvedBy);
    }
    /**
     * This method sends the updated Charge Policy to the database.
     * @param chargePolicy This is the updated model whose ID remains unchanged
     * @return true when update is successful, false otherwise.
     */
    public boolean updateEntry(ChargePolicy chargePolicy, String chargePolicyCode){return this.chargePolicyDao.updateEntry(chargePolicy);}
    /**
     * This method deletes the entry in the database
     * @param chargePolicyCode Policy Code of the Charge Policy to be deleted
     * @return 0 if there is no entry corresponding to the following code. 1, if successfully deleted.
     */
    public void deleteChargePolicy(String chargePolicyCode){this.chargePolicyDao.deleteChargePolicy(chargePolicyCode);}

    /**
     * This method is used to populate the Charge Code Column in the view
     * @return A List of Charge Codes which is the foreign key in the Charge Policy Table
     */
    public List<String> getChargeCodesList(){
        List<NewCharge> listofNewCharges = this.chargeDao.getChargeList();
        List<String> chargeCodeList = new ArrayList<>();
        for(NewCharge newCharge : listofNewCharges){
            if(newCharge.getStatus().equalsIgnoreCase("Approved"))
                chargeCodeList.add(newCharge.getChargeCode());
        }
        return  chargeCodeList;
    }

    /**
     * This method uses the database layer of the Charge Module to get information about a charge
     * @param chargeCode This is the Charge Code of the Charge whose Charge Code Name needs to be returned
     * @return Charge Code Name of the Charge with corresponding chargeCode
     */
    public String getChargeCodeName(String chargeCode){
        List<NewCharge> listofNewCharges = this.chargeDao.getChargeList();
        if(listofNewCharges==null||listofNewCharges.isEmpty()){
            return  null;
        }
        String chargeCodeName = null;
        for(NewCharge newCharge : listofNewCharges){
            if(newCharge.getChargeCode().equals(chargeCode)){
                chargeCodeName = newCharge.getChargeCodeName();
                break;
            }
        }
        return  chargeCodeName;
    }


}
