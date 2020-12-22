package com.nucleus.charge.service;

import com.nucleus.charge.dao.ChargeDao;
import com.nucleus.charge.model.NewCharge;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    ChargeDao chargeDao;

    @Override
    public boolean insertCharge(NewCharge charge, String status) {
        return chargeDao.insert(charge, status);
    }

    @Override
    public List<NewCharge> getChargeList() {
        return chargeDao.getChargeList();
    }

    @Override
    public List<NewCharge> getPendingChargeList() {
        return chargeDao.getPendingChargeList();
    }

    @Override
    public boolean deleteCharge(String chargeCode) {
        return chargeDao.deleteCharge(chargeCode);
    }

    @Override
    public NewCharge getOneCharge(String chargeCode) {
        return chargeDao.getOneCharge(chargeCode);
    }

    @Override
    public void updateStatus(String chargeCode, String status) {
        chargeDao.updateStatus(chargeCode, status);
    }

    @Override
    public boolean updateCharge(NewCharge charge) {
        NewCharge oldCharge = chargeDao.getOneCharge(charge.getChargeCode());
        oldCharge.setChargeDescription(charge.getChargeDescription());
        oldCharge.setChargePaymentType(charge.getChargePaymentType());
        oldCharge.setChargeType(charge.getChargeType());
        oldCharge.setChargeAmount(charge.getChargeAmount());
        oldCharge.setStatus(charge.getStatus());
        oldCharge.setAuthorizedDate(LocalDate.now());
        oldCharge.setAuthorizedBy(new LoginDetailsImpl().getUserName());
        return chargeDao.updateCharge(charge);
    }
}
