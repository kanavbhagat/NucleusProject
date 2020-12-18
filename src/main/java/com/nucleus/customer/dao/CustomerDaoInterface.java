package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;

import java.util.List;

public interface CustomerDaoInterface {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    List<Customer> listCustomer();
    boolean removeCustomer(Customer customer);
    boolean removeCustomer(String id);
    Customer getCustomerById(String id);
}
