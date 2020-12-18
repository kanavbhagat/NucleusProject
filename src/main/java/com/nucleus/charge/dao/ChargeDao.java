package com.nucleus.charge.dao;

import com.nucleus.charge.model.NewCharge;

import java.util.List;

public interface ChargeDao {
    public boolean insert(NewCharge charge);
    public List<NewCharge> getChargeList();
}
