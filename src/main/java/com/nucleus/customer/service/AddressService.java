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

    public boolean updateAddress(Address address){
        return addressDao.updateAddress(address);
    }
    public boolean removeAddress(Address address){
        return addressDao.removeAddress(address);
    }

    public boolean removeAddress(int id){
        return addressDao.removeAddress(id);
    }
}
