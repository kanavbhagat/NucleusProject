package com.nucleus.loanapplications.service;

import com.nucleus.loanapplications.dao.LoanApplicationDAO;
import com.nucleus.loanapplications.model.LoanApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanApplicationService {

    @Autowired
    NewLoanApplicationService newLoanApplicationService;

    public List<LoanApplications> getAllLoanApplicationsList(){
        return newLoanApplicationService.getLoanApplicationList();
    }

    public LoanApplications getLoanApplicationId(Integer id){
        return loanApplicationDAO.getLoanApplicationId(id);
    }
}
