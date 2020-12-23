package com.nucleus.customer.service;

import com.nucleus.customer.dao.AddressDao;
import com.nucleus.customer.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressDao addressDao;

    public boolean insertAddress(Address address){
       return addressDao.insertAddress(address);
    }
}
