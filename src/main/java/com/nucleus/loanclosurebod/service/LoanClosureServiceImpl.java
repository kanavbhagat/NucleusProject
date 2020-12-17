package com.nucleus.loanclosurebod.service;

import com.nucleus.loanclosurebod.database.LoanClosureDao;
import com.nucleus.loanclosurebod.model.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanClosureServiceImpl implements LoanClosureService {

    @Autowired
    LoanClosureDao loanClosureDao;

    @Override
    public int function1() {
        return 0;
    }

    @Override
    public List<LoanApplication> function2(int loanId) {
        return null;
    }

    @Override
    public void funtion3() {

    }

    @Override
    public void addDummyData() {
        loanClosureDao.addDummyData();
    }

    @Override
    public void updateStatus(){
        loanClosureDao.updateStatus();
    }
}
