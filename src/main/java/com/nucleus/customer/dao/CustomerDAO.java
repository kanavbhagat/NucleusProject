package com.nucleus.customer.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.product.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

            try(Session session = getSession()) {
                session.beginTransaction();
                Customer customer = session.get(Customer.class , id);
                return customer;
            }


    }
}
