package com.nucleus.loanclosurebod.database;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;

import java.util.List;

public interface LoanClosureDao {

    public List<LoanApplications> getLoanApplications();
    public List<RepaymentSchedule> getRepaymentSchedule(LoanApplications loanApplication);
    public boolean updateStatus(LoanApplications loanApplicationNumber, String newStatus);

    public void addDummyData();
}