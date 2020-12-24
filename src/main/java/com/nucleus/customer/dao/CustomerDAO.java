package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import jdk.javadoc.internal.doclets.toolkit.util.ClassUseMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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

        boolean successful = false;
        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
            session.close();
            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                session.update(customer);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }

        }
    }
    @Override
    public List<Customer> listCustomer() {
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<Customer> query = session.createQuery("from Customer c");
            List<Customer> customerList = query.list();
            session.getTransaction().commit();
            return customerList;
        }
    }




    @Override
    public boolean removeCustomer(Customer customer) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                session.remove(customer);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }

        }

    }

    @Override
    public boolean removeCustomer(String id) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                Customer customer = session.get(Customer.class , id);
                session.remove(customer);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }

        }
    }

    @Override
    public Customer getCustomerById(String id) {
       Customer customer = null;

        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            customer = session.get(Customer.class , id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return customer;
    }
    public List<LoanApplications> getCustomerLoanDetails(String customerCode){
        Customer customer = null;
        List<LoanApplications> loanApplications = new ArrayList<>();
        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            customer = session.get(Customer.class , customerCode);
            loanApplications = customer.getLoanApplications();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
      return loanApplications;
    }
}
