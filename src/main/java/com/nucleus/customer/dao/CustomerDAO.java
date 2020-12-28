package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
//import jdk.javadoc.internal.doclets.toolkit.util.ClassUseMapper;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class acts as a Database layer for all
 * Customer related operations.
 *
 */
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

    /**
     * This method is used to add Customer to database.
     *
     * @param c This contains the object of Customer class
     *
     * @return boolean This returns a true/false based on whether the customer
     *          was successfully added or not.
     */
    @Override
    public boolean addCustomer(Customer c) {

        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            try {
                session.save(c);
                session.getTransaction().commit();
                session.close();
                return true;
            } catch (Exception e){
                e.printStackTrace();
                session.getTransaction().rollback();
                session.close();
                return false;
            }
        }
    }

    /**
     * This method is used to UPDATE Customer details in database.
     *
     * @param customer This contains the Customer object with details to be
     *                 updated with
     *
     * @return Boolean This returns a true/false based on whether the customer
     *                 was successfully UPDATED or not.
     */

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


    /**
     * This method is used to get list of Customers and their details from database.
     *
     * @return Customer This returns list of objects of Customer class with the details of
     *         all customers.
     */
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


    /**
     * This method is used to REMOVE Customer from database.
     *
     * @param customer This contains the object of Customer
     *                 to be removed
     *
     * @return Boolean This returns a true/false based on whether the Removal
     *                 was successful or not.
     */

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

    /**
     * This method is used to REMOVE Customer from database.
     *
     * @param id This contains the ID of Customer
     *                 to be removed
     *
     * @return Boolean This returns a true/false based on whether the Removal
     *                 was successful or not.
     */
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

    /**
     * This method is used to get Customer's details from database.
     *
     * @param id This contains the ID of Customer
     *           to retrieve its details from database
     *
     * @return Customer This returns list of objects of Customer class with the details of
     *         all customers.
     */
    @Override
    public Customer getCustomerById(String id) {
       Customer customer = null;

        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            customer = session.get(Customer.class , id);
            if(customer==null)
                return customer;
            Hibernate.initialize(customer.getAddresses());
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * This method is used to get a List of Customer's Loan applications from database.
     *
     * @param customerCode This contains the ID of Customer
     *           to retrieve details of its loan applications from database
     *
     * @return LoanApplications This returns list of objects of Loan Applications class with the details of
     *         requested customer's loan applications.
     */
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
