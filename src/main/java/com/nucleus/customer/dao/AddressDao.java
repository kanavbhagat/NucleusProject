package com.nucleus.customer.dao;


import com.nucleus.customer.model.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao implements  AddressDaoInterface{


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
    public boolean insertAddress(Address address) {
        boolean successful = false;
        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(address);
            session.getTransaction().commit();
            session.close();
            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }
}
