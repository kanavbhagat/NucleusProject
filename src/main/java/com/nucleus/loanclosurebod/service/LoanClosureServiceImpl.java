package com.nucleus.loanclosurebod.service;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.database.LoanClosureDao;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This class implements the functions declared by the LoanClosureService
 * Interface.
 */
@Service
@PropertySource("classpath:status.properties")
public class LoanClosureServiceImpl implements LoanClosureService {

    @Autowired
    LoanClosureDao loanClosureDao;

    //Getting status field values from status.properties file:
    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.closed}"))
    private String closed;


    /**
     * Method for obtaining all the Loan Applications and calling
     * the closeOneLoan method for each Loan Application.
     */
    @Override
    public int loanClosureBod() {
        List<LoanApplications> loanApplications = loanClosureDao.getLoanApplications();
        boolean loanClosureStatus;
        int countofClosedLoans = 0;
        for (LoanApplications loanApplication : loanApplications) {
            loanClosureStatus = closeOneLoan(loanApplication);
            if(loanClosureStatus){
                countofClosedLoans++;
            }
        }
        return countofClosedLoans;
    }

    /* Method for obtaining the repayment schedule for the given Loan Application
    and updating the status of the loan based on Bill Flags. */
    private boolean closeOneLoan(LoanApplications loanApplication){
        boolean flag = true;
        boolean closureStatus = false;
        String currentStatus = loanApplication.getStatus();
        //Loan cannot be closed if it is pending/inactive or already closed
        if (!currentStatus.equalsIgnoreCase(approved)) {
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
            closureStatus = loanClosureDao.updateStatus(loanApplication, closed);
        }
        return closureStatus;
    }
}