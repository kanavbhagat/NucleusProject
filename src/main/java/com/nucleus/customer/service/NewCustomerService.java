package com.nucleus.customer.service;


import com.nucleus.customer.dao.CustomerDAO;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Service
public class NewCustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public Boolean createNewCustomer(Customer customer){
        return customerDAO.addCustomer(customer);
    }
}
