package com.nucleus.customerservice.loandisbursal.database;

import com.nucleus.customerservice.loandisbursal.model.Customer;
import com.nucleus.customerservice.loandisbursal.model.LoanApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LoanDisbursalDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public LoanApplication getLoanDetails(int loanApplicationNumber){
        Session session = sessionFactory.openSession();
        LoanApplication loanApplication = session.get(LoanApplication.class, loanApplicationNumber);
        return loanApplication;
    }

    public Set<LoanApplication> getCustomerLoanDetails(String customerCode){
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, customerCode);
        return customer.getLoanApplications();
    }
}
