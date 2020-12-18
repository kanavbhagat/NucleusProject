package com.nucleus.customer.service;


import com.nucleus.customer.dao.CustomerDAO;
import com.nucleus.customer.dao.CustomerDaoInterface;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NewCustomerService {

    @Autowired
    private CustomerDaoInterface customerDAO;

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

    public Boolean updateCustomer(Customer customer){
        return customerDAO.updateCustomer(customer);
    }

    public List<Customer> getCustomerList(){
        return customerDAO.listCustomer();
    }

    public boolean removeCustomer(Customer customer){
        return customerDAO.removeCustomer(customer);
    }

    public boolean removeCustomer(String id){
        return customerDAO.removeCustomer(id);
    }

    public Customer getCustomerById(String id){
        return customerDAO.getCustomerById(id);
    }
}
