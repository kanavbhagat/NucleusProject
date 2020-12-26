package com.nucleus.loanapplications.service;

import com.nucleus.loanapplications.dao.LoanApplicationDAO;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This class acts as a Service layer for new
 * Loan applications related operations.
 *
 */
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

    /**
     * This method is used to add Loan Application details to database.
     *
     * @param loanApplications This contains the object of
     *                         LoanApplication class
     *
     * @return boolean This returns a true/false based on whether the Application
     *          was successfully added or not.
     */
    public boolean addLoanApplication(LoanApplications loanApplications){
        return loanApplicationDAO.addApplication(loanApplications);
    }

    /**
     * This method is used to get list of Loan Applications and their details from database.
     *
     * @return LoanApplications This returns list of objects of LoanApplications class with the details of
     *         all the Loan Applications.
     */
    public List<LoanApplications> getLoanApplicationList(){
        return loanApplicationDAO.getLoanApplicationList();
    }
}
