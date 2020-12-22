package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;

import java.util.List;
import java.util.Set;

public interface CustomerDaoInterface {
    boolean addCustomer(Customer c);
    boolean updateCustomer(Customer customer);
    List<Customer> listCustomer();
    boolean removeCustomer(Customer customer);
    boolean removeCustomer(String id);
    Customer getCustomerById(String id);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
}
