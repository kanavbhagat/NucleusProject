package com.nucleus.loanclosurebod.database;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;

import java.util.List;
import java.util.Set;

public interface LoanClosureDao {

    public List<LoanApplications> getLoanApplications();
    public List<RepaymentSchedule> getRepaymentSchedule(LoanApplications loanApplication);
    public boolean updateStatus(LoanApplications loanApplicationNumber, String newStatus);

    public LoanApplications getLoanDetails(int loanApplicationNumber);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
    //public void addDummyData();
}