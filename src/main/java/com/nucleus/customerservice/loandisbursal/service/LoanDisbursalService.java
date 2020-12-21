package com.nucleus.customerservice.loandisbursal.service;

import com.nucleus.loanapplications.model.LoanApplications;

import java.util.List;

public interface LoanDisbursalService {
    public LoanApplications getLoanDetails(int loanApplicationNumber);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
}
