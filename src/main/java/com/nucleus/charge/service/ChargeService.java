package com.nucleus.charge.service;

import com.nucleus.charge.model.NewCharge;
import java.util.List;

public interface ChargeService {
    public boolean insertCharge(NewCharge charge);
    public List<NewCharge> getChargeList();
}
