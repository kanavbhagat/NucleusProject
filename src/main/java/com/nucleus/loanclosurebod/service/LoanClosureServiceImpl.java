package com.nucleus.loanclosurebod.service;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.database.LoanClosureDao;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This class implements the functions declared by the LoanClosureService
 * Interface.
 */
@Service
public class LoanClosureServiceImpl implements LoanClosureService {

    @Autowired
    LoanClosureDao loanClosureDao;

    /**
     * Method for obtaining an object of LoanClosureDao Interface
     * @return loanClosureDao
     */
    public LoanClosureDao getLoanClosureDao() {
        return loanClosureDao;
    }

    /**
     * Method for setting an object of LoanClosureDao Interface
     */
    public void setLoanClosureDao(LoanClosureDao loanClosureDao) {
        this.loanClosureDao = loanClosureDao;
    }

    /**
     * Method for obtaining all the Loan Applications and calling
     * the closeOneLoan method for each Loan Application.
     */
    @Override
    public void loanClosureBod() {
        List<LoanApplications> loanApplications = loanClosureDao.getLoanApplications();
        for (LoanApplications loanApplication : loanApplications) {
            closeOneLoan(loanApplication);
        }
    }

    /**
     * Method for obtaining the repayment schedule for the given Loan Application
     * and updating the status of the loan based on Bill Flags.
     * @param loanApplication
     * @return closureStatus
     */
    public boolean closeOneLoan(LoanApplications loanApplication){
        boolean flag = true;
        boolean closureStatus = false;
        String currentStatus = loanApplication.getStatus();
        //Loan cannot be closed if it is pending/inactive or already closed
        if (currentStatus.equalsIgnoreCase("Closed") ||
                currentStatus.equalsIgnoreCase("Pending") ||
                currentStatus.equalsIgnoreCase("Inactive")) {
            flag = false;
        } else {
            /*Iterating through the repayment schedule and checking if all the Bill Flags are Y
            or not. Break through the loop if any Bill Flag is N.
             */
            List<RepaymentSchedule> repaymentSchedules = loanClosureDao.getRepaymentSchedule(loanApplication);
            for (RepaymentSchedule repaymentScheduleEntry : repaymentSchedules) {
                if (repaymentScheduleEntry.getBillFlag().equalsIgnoreCase("N")) {
                    flag = false;
                    break;
                }
            }
        }
        //Update the loan status as CLOSED if all the Bill Flags are Y.
        if (flag) {
            closureStatus = loanClosureDao.updateStatus(loanApplication, "Closed");
        }
        return closureStatus;
    }
}