package com.nucleus.customerservice.repaymentschedulereport.service;


import com.nucleus.customerservice.repaymentschedulereport.dao.RepaymentScheduleReportDAO;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a Service layer for getting Repayment Schedule Report .
 *
 */
@Service
public class RepaymentScheduleReportServiceImpl implements RepaymentScheduleReportService {

    @Autowired
    private RepaymentScheduleReportDAO repaymentScheduleDAO;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }


    /**
     * Retrieves a Repayment Schedule Report by loan application number from the database.
     * @param loanApplicationNumber is the Loan Application Number of the Repayment Schedule to be retrieved.
     * @return List of given Repayment Schedule if exists, else null.
     */
    @Override
    public List<RepaymentSchedule> getRepaymentScheduleReport(int loanApplicationNumber) {
        List<RepaymentSchedule> repaymentScheduleReport = repaymentScheduleDAO.getRepaymentScheduleReport(loanApplicationNumber);
        return repaymentScheduleReport;
    }
}