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

}