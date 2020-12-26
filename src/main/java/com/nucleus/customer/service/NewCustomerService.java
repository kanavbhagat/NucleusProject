package com.nucleus.customer.service;


import com.nucleus.customer.dao.CustomerDAO;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

/**
 * This class acts as a Service layer for all
 * Customer related operations.
 *
 */

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

    /**
     * This method is used to add new Customer to database.
     *
     * @param customer This contains the object of
     *
     * @return boolean This returns a true/false based on whether the customer
     *          was successfully added or not.
     */
    public Boolean createNewCustomer(Customer customer){
        return customerDAO.addCustomer(customer);
    }


    /**
     * This method is used to get Customer details from database.
     *
     * @param id This contains the Customer id whose details is to be returned
     *
     * @return Customer This returns an object of Customer class with the details of
     *         requested customer.
     */
    public Customer getCustomer(String id){
        return customerDAO.getCustomerById(id);
    }
}
