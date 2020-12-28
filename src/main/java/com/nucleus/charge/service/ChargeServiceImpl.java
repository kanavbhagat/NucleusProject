package com.nucleus.charge.service;

import com.nucleus.charge.dao.ChargeDao;
import com.nucleus.charge.model.NewCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This class acts as a Service layer for all
 * Charge related operations.
 *
 */
@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeDao chargeDao;

    /**
     * This method is used to add a new Charge to database.
     *
     * @param charge This is the model that has to be added to the database.
     * @param status This contains status of Charge that needs to be set.
     *
     * @return boolean This returns a true/false based on whether the object was successfully added or not.
     */
    @Override
    public int insertCharge(NewCharge charge, String status) {
        return chargeDao.insert(charge, status);
    }

    /**
     * This method is used to get a list of all Charges.
     *
     * @return List This returns a list of all charges in the database.
     */
    @Override
    public List<NewCharge> getChargeList() {
        return chargeDao.getChargeList();
    }

    /**
     * This method is used to get a list of all pending Charges.
     *
     * @return List This returns a list of all pending charges in the database.
     */
    @Override
    public List<NewCharge> getPendingChargeList() {
        return chargeDao.getPendingChargeList();
    }

    /**
     * This method is used to delete an existing Charge.
     *
     * @param chargeCode This contains the chargeCode of the
     *                   Charge that is to be deleted.
     *
     * @return boolean This returns a true/false based on whether the charge was successfully deleted or not.
     */
    @Override
    public boolean deleteCharge(String chargeCode) {
        return chargeDao.deleteCharge(chargeCode);
    }

    /**
     * This method is used to retrieve one Charge by Charge Code.
     *
     * @param chargeCode This contains the chargeCode
     *                   for which Charge is to be fetched.
     *
     * @return NewCharge This returns the Charge that was required.
     */
    @Override
    public NewCharge getOneCharge(String chargeCode) {
        return chargeDao.getOneCharge(chargeCode);
    }

    /**
     * This method is used to update status {Approve/Reject} of Charge.
     *
     * @param chargeCode This contains the chargeCode
     *                   for which status is to be changed.
     * @param status This holds one of two status,
     *               (i.e. Approve/Reject), that decide the status.
     *
     * @return boolean This returns a true/false based on whether the status was successfully changed or not.
     */
    @Override
    public boolean updateStatus(String chargeCode, String status) {
        return chargeDao.updateStatus(chargeCode, status);
    }

    /**
     * This method is used to update an existing Charge.
     *
     * @param charge This is the Charge
     *                          that has to be updated.
     *
     * @return boolean This returns a true/false based on whether the charge was successfully updated or not.
     */
    @Override
    public boolean updateCharge(NewCharge charge) {
        return chargeDao.updateCharge(charge);
    }
}
