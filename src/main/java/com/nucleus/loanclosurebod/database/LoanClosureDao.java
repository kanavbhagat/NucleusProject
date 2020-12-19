package com.nucleus.loanclosurebod.database;

import com.nucleus.loanclosurebod.model.LoanApplication;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;

import java.util.List;

public interface LoanClosureDao {

    public List<LoanApplication> getLoanApplications();
    public List<RepaymentSchedule> getRepaymentSchedule(LoanApplication loanApplication);
    public boolean updateStatus(LoanApplication loanApplicationNumber, String newStatus);

    public void addDummyData();
}