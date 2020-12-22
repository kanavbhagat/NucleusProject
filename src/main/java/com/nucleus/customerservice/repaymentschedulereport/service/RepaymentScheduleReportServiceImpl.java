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

    static LocalDate returnDate(String date) {
        LocalDate dt = LocalDate.parse(date);
        return dt;
    }

    @Override
    public List<RepaymentSchedule> getRepaymentScheduleReport(int loanApplicationNumber) {
        List<RepaymentSchedule> repaymentScheduleReport = repaymentScheduleDAO.getRepaymentScheduleReport(loanApplicationNumber);
        return repaymentScheduleReport;
    }
}