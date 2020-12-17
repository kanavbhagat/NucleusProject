package com.nucleus.customerservice.loandisbursal.service;

import com.nucleus.customerservice.loandisbursal.database.LoanDisbursalDAO;
import com.nucleus.customerservice.loandisbursal.model.LoanApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class LoanDisbursalService {
    @Autowired
    private LoanDisbursalDAO loanDisbursalDao;

    public LoanApplication getLoanDetails(int loanApplicationNumber){
        return loanDisbursalDao.getLoanDetails(loanApplicationNumber);
    }

    public Set<LoanApplication> getCustomerLoanDetails(String customerCode){
        return loanDisbursalDao.getCustomerLoanDetails(customerCode);
    }
}
