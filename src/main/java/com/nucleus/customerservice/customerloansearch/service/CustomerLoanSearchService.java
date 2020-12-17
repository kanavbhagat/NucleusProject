package com.nucleus.customerservice.customerloansearch.service;

import com.nucleus.customer.model.Customer;
import com.nucleus.customerservice.customerloansearch.dao.CustomerDAO;
import com.nucleus.customerservice.customerloansearch.dao.LoanApplicationsDAO;
import com.nucleus.loanaplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoanSearchService {

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    LoanApplicationsDAO loanApplicationsDAO;

    public Customer getCustomerDetails(String customerCode){
        return customerDAO.getCustomerDetails(customerCode);
    }

    public LoanApplications getLoanApplicationDetails(Integer loanApplicationNumber){
        return loanApplicationsDAO.getLoanApplicationDetails(loanApplicationNumber);
    }
}
