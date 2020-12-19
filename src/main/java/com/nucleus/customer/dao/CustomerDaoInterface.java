package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;

import java.util.List;
import java.util.Set;

public interface CustomerDaoInterface {
    boolean addCustomer(Customer c);

    boolean updateCustomer(Customer customer);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
}
