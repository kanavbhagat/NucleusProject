package com.nucleus.loanclosurebod.service;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanapplications.service.LoanApplicationService;
import com.nucleus.loanclosurebod.database.LoanClosureDao;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * This class implements the functions declared by the LoanClosureService
 * Interface.
 */
@Service
public class LoanClosureServiceImpl implements LoanClosureService {

    @Autowired
    LoanClosureDao loanClosureDao;

    @Autowired
    LoanApplicationService loanApplicationService;

    /**
     * Method for obtaining all the Loan Applications and calling
     * the closeOneLoan method for each Loan Application.
     */
    @Override
    public int loanClosureBod() {
        List<LoanApplications> loanApplications = loanApplicationService.getAllLoanApplicationsList();
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
        if (!currentStatus.equalsIgnoreCase("Active")) {
            flag = false;
        } else {
            /*Iterating through the repayment schedule and checking if all the Bill Flags are Y
            or not. Break through the loop if any Bill Flag is N.
             */
            List<RepaymentSchedule> repaymentSchedules = loanClosureDao.getRepaymentSchedule(loanApplication.getLoanApplicationNumber());
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


    @Override
    public LoanApplications getLoanDetails(int loanApplicationNumber){
        return loanClosureDao.getLoanDetails(loanApplicationNumber);
    }

    @Override
    public List<LoanApplications> getCustomerLoanDetails(String customerCode){
        return loanClosureDao.getCustomerLoanDetails(customerCode);
    }
//    @Override
//    public void addDummyData() {
//        loanClosureDao.addDummyData();
//    }

}