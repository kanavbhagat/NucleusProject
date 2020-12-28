package com.nucleus.charge.service;

import com.nucleus.charge.model.NewCharge;
import java.util.List;

public interface ChargeService {
    int insertCharge(NewCharge charge, String status);
    List<NewCharge> getChargeList();
    List<NewCharge> getPendingChargeList();
    boolean deleteCharge(String chargeCode);
    NewCharge getOneCharge(String chargeCode);
    boolean updateStatus(String chargeCode,String status);
    boolean updateCharge(NewCharge charge);

}
