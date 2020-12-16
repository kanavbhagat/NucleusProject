package com.nucleus.loanclosurebod.database;

import com.nucleus.loanclosurebod.model.LoanApplication;

import java.util.List;

public interface LoanClosureDao {

    public int function1();
    public List<LoanApplication> function2(int loanId);
    public void function3();
    public void addDummyData();

    public void updateStatus();
}
