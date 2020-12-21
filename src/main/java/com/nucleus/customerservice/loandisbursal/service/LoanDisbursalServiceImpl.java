package com.nucleus.customerservice.loandisbursal.service;

import com.nucleus.customerservice.loandisbursal.database.LoanDisbursalDAO;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanDisbursalServiceImpl implements LoanDisbursalService{
    @Autowired
    private LoanDisbursalDAO loanDisbursalDao;

    @Override
    public LoanApplications getLoanDetails(int loanApplicationNumber){
        return loanDisbursalDao.getLoanDetails(loanApplicationNumber);
    }

    @Override
    public List<LoanApplications> getCustomerLoanDetails(String customerCode){
        return loanDisbursalDao.getCustomerLoanDetails(customerCode);
    }
}
