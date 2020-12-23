package com.nucleus.loanclosurebod.database;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import java.util.List;
import java.util.Set;

/**
 * This is a DAO Interface that declares the functionality
 * of the LoanClosureDaoImpl Class.
 */
public interface LoanClosureDao {

    List<RepaymentSchedule> getRepaymentSchedule(int loanApplicationNumber);

    boolean updateStatus(LoanApplications loanApplication, String newStatus);


    public LoanApplications getLoanDetails(int loanApplicationNumber);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
    //public void addDummyData();

}