package com.nucleus.customerservice.repaymentschedulereport.dao;

import com.nucleus.repaymentschedule.model.RepaymentSchedule;

import java.util.List;


public interface RepaymentScheduleReportDAO {
    public List<RepaymentSchedule> getRepaymentScheduleReport(int loanApplicationNumber);
}


