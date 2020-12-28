package com.nucleus.loanapplications.service;

import com.nucleus.loanapplications.dao.LoanApplicationDAO;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class acts as a Service layer for all
 * Loan Application related operations.
 *
 */
@Service
public class LoanApplicationService {

    @Autowired
    LoanApplicationDAO loanApplicationDAO;

    /**
     * This method is used to get list of Loan Applications and their details from database.
     *
     * @return LoanApplications This returns list of objects of LoanApplications class with the details of
     *         all the Loan Applications.
     */
    public List<LoanApplications> getAllLoanApplicationsList(){
        return loanApplicationDAO.getLoanApplicationList();
    }

    /**
     * This method is used to get Application details from database.
     *
     * @param id This contains the ID of Loan Application
     *           to retrieve its details from database
     *
     * @return LoanApplications This returns list of objects of Loan Applications
     *          class with the details of all loan applications.
     */
    public LoanApplications getLoanApplicationId(Integer id){
        return loanApplicationDAO.getLoanApplicationId(id);
    }

    /**
     * This method is used to REMOVE Loan Application from database.
     *
     * @param id This contains the ID of Loan Application
     *                 to be removed
     *
     * @return Boolean This returns a true/false based on whether the Removal
     *                 was successful or not.
     */
    public boolean deleteLoanApplicationId(Integer id){
    	return loanApplicationDAO.deleteLoanApplication(id);
    }

    /**
     * This method is used to UPDATE Loan Application details in database.
     *
     * @param loanApplications This contains the Loan Application object with details to be
     *                 updated with
     *
     * @return Boolean This returns a true/false based on whether the Loan Application
     *                 was successfully UPDATED or not.
     */
    public boolean updateLoanApplication(LoanApplications loanApplications){
        return loanApplicationDAO.updateLoanApplication(loanApplications);
    }
}
