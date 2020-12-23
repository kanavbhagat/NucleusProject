package com.nucleus.customer.dao;

import com.nucleus.customer.model.Address;

public interface AddressDaoInterface {
    boolean insertAddress(Address address);
    boolean updateAddress(Address address);
    boolean removeAddress(Address address);
    boolean removeAddress(int id);
}
