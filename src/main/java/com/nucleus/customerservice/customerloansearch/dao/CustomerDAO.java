package com.nucleus.customerservice.customerloansearch.dao;


import com.nucleus.customer.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements CustomerDAOInterface{

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
    public Customer getCustomerDetails(String customerCode) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                Customer customer= session.get(Customer.class,customerCode);
                session.getTransaction().commit();
                return customer;
            }catch(HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
            }

            return null;
        }

    }
}




