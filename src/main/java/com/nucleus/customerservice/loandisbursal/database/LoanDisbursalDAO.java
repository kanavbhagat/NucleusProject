package com.nucleus.customerservice.loandisbursal.database;

import com.nucleus.loanapplications.model.LoanApplications;
import java.util.List;

public interface LoanDisbursalDAO {
    public LoanApplications getLoanDetails(int loanApplicationNumber);
    public List<LoanApplications> getCustomerLoanDetails(String customerCode);
}
