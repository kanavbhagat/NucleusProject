package com.nucleus.loanapplications.service;

import com.nucleus.loanapplications.dao.LoanApplicationDAO;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {

    @Autowired
    LoanApplicationDAO loanApplicationDAO;

    public List<LoanApplications> getAllLoanApplicationsList(){
        return loanApplicationDAO.getLoanApplicationList();
    }
}
