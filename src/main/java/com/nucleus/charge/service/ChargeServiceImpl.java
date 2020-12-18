package com.nucleus.charge.service;

import com.nucleus.charge.dao.ChargeDao;
import com.nucleus.charge.model.NewCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    ChargeDao chargeDao;

    @Override
    public boolean insertCharge(NewCharge charge) {
        if(chargeDao.insert(charge)) {
            return true;
        }
        return false;
    }

    @Override
    public List<NewCharge> getChargeList() {
        return chargeDao.getChargeList();
    }
}
