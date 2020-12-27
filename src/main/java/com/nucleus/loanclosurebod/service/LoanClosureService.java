package com.nucleus.loanclosurebod.service;

import com.nucleus.loanapplications.model.LoanApplications;

import java.util.List;

/**
 * This is a Service Interface that declares the functionality
 * of the LoanClosureServiceImpl Class.
 */
public interface LoanClosureService {

    int loanClosureBod();

    public LoanApplications getLoanDetails(int loanApplicationNumber);

    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
}