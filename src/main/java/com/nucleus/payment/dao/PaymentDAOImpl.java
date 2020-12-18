package com.nucleus.payment.dao;

import com.nucleus.payment.model.Payment;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDAOImpl implements PaymentDAO{
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E){
            session = sessionFactory.openSession();
        }
        return session;
    }

    public boolean insertPayment(Payment payment){
        boolean insertStatus;
        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(payment);
            session.getTransaction().commit();
            insertStatus = true;
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
            insertStatus = false;
        }
        return insertStatus;
    }

//    public List<Payment> getPaymentsList(){
//        List<Payment> paymentList;
//        try {
//            Session session  = getSession();
//            session.beginTransaction();
//            Query<Payment> query = session.createQuery("from Payment p");
//            paymentList = query.list();
//            session.getTransaction().commit();
//        }
//        catch (HibernateException e){
//            paymentList = null;
//            e.printStackTrace();
//        }
//        return paymentList;
//    }
}
