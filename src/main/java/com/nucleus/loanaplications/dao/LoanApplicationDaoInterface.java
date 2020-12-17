package com.nucleus.loanaplications.dao;

import com.nucleus.customerservice.loandisbursal.model.LoanApplication;

import java.util.List;

public interface LoanApplicationDaoInterface {
    boolean addApplication(LoanApplication loanApplication);
    List<LoanApplication> getLoanApplicationList();
}
