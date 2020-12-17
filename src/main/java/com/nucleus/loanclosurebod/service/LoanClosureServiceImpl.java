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

    @Override
    public void loanClosureBod() {
        List<LoanApplication> loanApplications = loanClosureDao.getLoanApplications();
        if(loanApplications != null){
            for(LoanApplication loanApplication : loanApplications){
                boolean flag = true;
                if(loanApplication.getLoanStatus().equalsIgnoreCase("Closed") ||
                        loanApplication.getLoanStatus().equalsIgnoreCase("Pending") ||
                        loanApplication.getLoanStatus().equalsIgnoreCase("Inactive")){
                    flag = false;
                }
                if(flag) {
                    List<RepaymentSchedule> repaymentSchedules = loanClosureDao.getRepaymentSchedule(loanApplication);
                    for (RepaymentSchedule repaymentScheduleEntry : repaymentSchedules) {
                        if(repaymentScheduleEntry.getBillFlag().equalsIgnoreCase("N")){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag){
                    boolean val = loanClosureDao.updateStatus(loanApplication, "Closed");
                    System.out.println("Status Closed");
                }
            }
        }
    }

    @Override
    public void addDummyData() {
        loanClosureDao.addDummyData();
    }
}