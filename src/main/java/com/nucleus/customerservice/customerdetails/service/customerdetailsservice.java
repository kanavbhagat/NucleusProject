package com.nucleus.customerservice.customerdetails.service;

import com.nucleus.customer.dao.CustomerDaoInterface;
import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class customerdetailsservice {
    @Autowired
    private CustomerDaoInterface customerdao;

    public Customer getCustomerDetails(String customerCode){

        //////////////////////////////
        // TEST CODE ////////////////
        /////////////////////////////
//        Customer customer = new Customer();
//        customer.setFirstName("vsac");
//        customer.setLastName("wdwe");
//        customer.setCustomerCode("101");
//        customer.setDateOfBirth("02020202");
//        customer.setNationality("indian");
//        Address addr = new Address();
//        addr.setAddressId(1);
//        addr.setCity("fcd");
//        List<Address> adds = new ArrayList<Address>();
//        adds.add(addr);
//        customer.setAddresses(adds);
//        return customer;
        ////////////////////////////////

        return customerdao.getCustomerById(customerCode);

    }

}


