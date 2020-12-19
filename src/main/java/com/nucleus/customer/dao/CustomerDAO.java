package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CustomerDAO implements CustomerDaoInterface{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E){
            session = sessionFactory.openSession();
        }
        return session;
    }
    @Override
    public boolean addCustomer(Customer c) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                session.save(c);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }

        }
    }


    public Set<LoanApplications> getCustomerLoanDetails(String customerCode){
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, customerCode);
        return customer.getLoanApplications();
    }
}
