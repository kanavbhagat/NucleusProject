package com.nucleus.loanclosurebod.service;

import com.nucleus.loanclosurebod.database.LoanClosureDao;
import com.nucleus.loanclosurebod.model.LoanApplication;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanClosureServiceImpl implements LoanClosureService {

    @Autowired
    LoanClosureDao loanClosureDao;

    public LoanClosureDao getLoanClosureDao() {
        return loanClosureDao;
    }

    public void setLoanClosureDao(LoanClosureDao loanClosureDao) {
        this.loanClosureDao = loanClosureDao;
    }

    @Override
    public void loanClosureBod() {
        List<LoanApplication> loanApplications = loanClosureDao.getLoanApplications();
        for (LoanApplication loanApplication : loanApplications) {
            closeOneLoan(loanApplication);
        }
    }

    public boolean closeOneLoan(LoanApplication loanApplication){
        boolean flag = true;
        boolean closureStatus = false;
        String currentStatus = loanApplication.getLoanStatus();
        if (currentStatus.equalsIgnoreCase("Closed") ||
                currentStatus.equalsIgnoreCase("Pending") ||
                currentStatus.equalsIgnoreCase("Inactive")) {
            flag = false;
        } else {
            List<RepaymentSchedule> repaymentSchedules = loanClosureDao.getRepaymentSchedule(loanApplication);
            for (RepaymentSchedule repaymentScheduleEntry : repaymentSchedules) {
                if (repaymentScheduleEntry.getBillFlag().equalsIgnoreCase("N")) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            closureStatus = loanClosureDao.updateStatus(loanApplication, "Closed");
        }
        return closureStatus;
    }

    @Override
    public void addDummyData() {
        loanClosureDao.addDummyData();
    }
}