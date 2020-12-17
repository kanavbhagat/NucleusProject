package com.nucleus.customerservice.customerloansearch.dao;


import com.nucleus.customer.model.Customer;

public interface CustomerDAOInterface {
    Customer getCustomerDetails(String customerCode);
}