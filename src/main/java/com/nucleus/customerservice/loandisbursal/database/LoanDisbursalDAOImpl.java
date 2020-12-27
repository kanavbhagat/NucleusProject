package com.nucleus.customerservice.loandisbursal.database;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanDisbursalDAOImpl implements LoanDisbursalDAO{

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get all Details of a Loan by loanApplicationNumber
     * @param loanApplicationNumber
     * @return Object of LoanApplications Class
     */
    @Override
    public LoanApplications getLoanDetails(int loanApplicationNumber){
        LoanApplications loanApplication=null;
        try(Session session = getSession()) {
            session.beginTransaction();
            loanApplication = session.get(LoanApplications.class, loanApplicationNumber);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return loanApplication;
    }

    /**
     * Get all Loans associated with a customer by customerCode
     * @param customerCode
     * @return List<LoanApplications> list contains all loans taken by this customer
     */
    @Override
    public List<LoanApplications> getCustomerLoanDetails(String customerCode){
        List<LoanApplications> loanApplications=null;
        try(Session session = getSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, customerCode);
            if(customer != null) {
                Hibernate.initialize(customer.getLoanApplications());
                loanApplications=customer.getLoanApplications();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return loanApplications;
    }

    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.openSession();
        } catch (HibernateException E){
            session = sessionFactory.getCurrentSession();
        }
        return session;
    }
}
