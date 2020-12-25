package com.nucleus.loanclosurebod.database;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;

import java.util.List;

/**
 * This is a DAO Interface that declares the functionality
 * of the LoanClosureDaoImpl Class.
 */
public interface LoanClosureDao {

    List<LoanApplications> getLoanApplications();

    List<RepaymentSchedule> getRepaymentSchedule(LoanApplications loanApplicationNumber);

    boolean updateStatus(LoanApplications loanApplication, String newStatus);


    /**
     * Get all Details of a closed Loan by loanApplicationNumber
     * @param loanApplicationNumber
     * @return Object of LoanApplications Class
     */
    public LoanApplications getLoanDetails(int loanApplicationNumber);

    /**
     * Get all closed Loans associated with a customer by customerCode
     * @param customerCode
     * @return list of LoanApplications contains all loans taken by this customer
     */
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);

}