package com.nucleus.customer.service;

import com.nucleus.customer.dao.AddressDao;
import com.nucleus.customer.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class acts as a Service layer for all
 * Address related operations.
 *
 */

@Service
public class AddressService {

    @Autowired
    AddressDao addressDao;

    /**
     * This method is used to insert address details in database.
     *
     * @param address This contains the object of Address class
     *                   with address details data provided in the object.
     *
     * @return boolean This returns a true/false based on whether the Address was
     *          successfully Inserted in database or not.
     */

    public boolean insertAddress(Address address){
       return addressDao.insertAddress(address);
    }
}
