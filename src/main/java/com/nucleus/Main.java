package com.nucleus;

import com.nucleus.customer.model.Customer;
import com.nucleus.customer.service.NewCustomerService;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setCustomerCode("L101");
        customer.setFirstName("hemant");
        customer.setLastName("sahay");
        //customer.setDateOfBirth("17-12-1997");
        customer.setNationality("indian");
        customer.setOccupationType("selfEmployed");
        customer.setOrganizationName("nucleus");
        customer.setTotalWorkExperience(5);

        NewCustomerService newCustomerService = new NewCustomerService();
        newCustomerService.createNewCustomer(customer);
    }
}
