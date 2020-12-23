package com.nucleus.loanapplications.dao;
import com.nucleus.loanapplications.model.LoanApplications;

import java.util.List;

public interface LoanApplicationDaoInterface {
    boolean addApplication(LoanApplications loanApplications);
    public List<LoanApplications> getLoanApplicationList();
    public LoanApplications getLoanApplicationId(Integer id);

}
