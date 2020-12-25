package com.nucleus.customerservice.customerdetails.service;

import com.nucleus.customer.dao.CustomerDaoInterface;
import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * customerdetailsservice class acts as a Controller layer for all RepaymentPolicy related operations.
 *
 * @author  Siddhant
 * @version 1.0
 * @since   2020-12-25
 */
@Service
public class customerdetailsservice {

    @Autowired
    private CustomerDaoInterface customerdao;

    /**
     * Fetches details of customer
     * @param customerCode Customer code to be searched
     * @return a customer Object containing all details.
     */
    public Customer getCustomerDetails(String customerCode){
        return customerdao.getCustomerById(customerCode);
    }

}


