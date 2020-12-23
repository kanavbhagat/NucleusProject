package com.nucleus.charge.dao;

import com.nucleus.charge.model.NewCharge;
import java.util.List;

public interface ChargeDao {
    boolean insert(NewCharge charge, String status);
    List<NewCharge> getChargeList();
    List<NewCharge> getPendingChargeList();
    boolean deleteCharge(String chargeCode);
    NewCharge getOneCharge(String chargeCode);
    boolean updateCharge(NewCharge charge);
    boolean updateStatus(String chargeCode, String status);

}
