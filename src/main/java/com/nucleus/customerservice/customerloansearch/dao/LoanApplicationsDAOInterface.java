package com.nucleus.customerservice.customerloansearch.dao;

import com.nucleus.loanaplications.model.LoanApplications;

public interface LoanApplicationsDAOInterface {

    LoanApplications getLoanApplicationDetails(Integer loanApplicationNumber);
}