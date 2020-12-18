package com.nucleus.loanapplications.service;

import com.nucleus.loanapplications.dao.LoanApplicationDAO;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NewLoanApplicationService {

    @Autowired
    LoanApplicationDAO loanApplicationDAO;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public boolean addLoanApplication(LoanApplications loanApplications){
        return loanApplicationDAO.addApplication(loanApplications);

    }
    public List<LoanApplications> getLoanApplicationList(){
        return loanApplicationDAO.getLoanApplicationList();
    }
}
