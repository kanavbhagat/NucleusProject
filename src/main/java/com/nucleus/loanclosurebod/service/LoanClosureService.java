package com.nucleus.loanclosurebod.service;


import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;

import java.util.List;

public interface LoanClosureService {

    public void loanClosureBod();
    //public void addDummyData();

    public LoanApplications getLoanDetails(int loanApplicationNumber);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);

}