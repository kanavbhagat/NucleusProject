package com.nucleus.customer.dao;


import com.nucleus.customer.model.Address;
import com.nucleus.customer.model.Customer;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
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

    @Override
    public boolean updateAddress(Address address) {
        boolean successful = false;
        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            /*Address address1 = session.get(Address.class , address.getAddressId());

            address = address1;
            */
            session.update(address);
            session.getTransaction().commit();
            session.close();
            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean removeAddress(Address address) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                session.remove(address);
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
    public boolean removeAddress(int id) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                Address address = session.get(Address.class , id);
                session.remove(address);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }

        }
    }
}
