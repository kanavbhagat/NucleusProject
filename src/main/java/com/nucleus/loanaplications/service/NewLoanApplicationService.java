package com.nucleus.loanaplications.service;

import com.nucleus.customerservice.loandisbursal.model.LoanApplication;
import com.nucleus.loanaplications.dao.LoanApplicationDAO;
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

    public boolean addLoanApplication(LoanApplication loanApplication){
        return loanApplicationDAO.addApplication(loanApplication);

    }
    public List<LoanApplication> getLoanApplicationList(){
        return loanApplicationDAO.getLoanApplicationList();
    }
}
