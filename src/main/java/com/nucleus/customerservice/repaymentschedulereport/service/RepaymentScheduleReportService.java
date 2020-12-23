package com.nucleus.customerservice.repaymentschedulereport.service;

import com.nucleus.repaymentschedule.model.RepaymentSchedule;

import java.util.List;

public interface RepaymentScheduleReportService {


    public List<RepaymentSchedule> getRepaymentScheduleReport(int loanApplicationNumber) ;
}
