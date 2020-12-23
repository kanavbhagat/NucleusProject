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

    public boolean updateStatus(int loanID, String suggestion, String user){
        boolean updateStatusValue = false;
        try{
            Payment payment = getPaymentById(loanID);
            payment.setPaymentStatus(suggestion);
            payment.setReviewedBy(user);
            Session session = getSession();
            session.beginTransaction();
            session.update(payment);
            session.getTransaction().commit();
            updateStatusValue = true;
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return updateStatusValue;
    }


    public void updatePayment(Payment payment){
        try {
            Session session = getSession();
            session.beginTransaction();
            System.out.println(payment.getCustomerCode());
            session.update(payment);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }

//        Payment existingPayment = getPaymentById(payment.getLoanApplicationNumber());
//        existingPayment.setPaymentCode(payment.getPaymentCode());
//        existingPayment.setPaymentAmount(payment.getPaymentAmount());
//        existingPayment.setPaymentDate(payment.getPaymentDate());
//        existingPayment.setPayoutBankAccount(payment.getPayoutBankAccount());
//        existingPayment.setRemarks(payment.getRemarks());
//        existingPayment.setPaymentChannel(payment.getPaymentChannel());
//
//        Session session = getSession();
//        session.beginTransaction();
//        session.update(existingPayment);
//        session.getTransaction().commit();
    }

    public List<Payment> getPaymentsList(){
        List<Payment> paymentList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<Payment> query = session.createQuery("from Payment p", Payment.class);
            paymentList = query.getResultList();
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            paymentList = null;
            e.printStackTrace();
        }
        return paymentList;
    }

    public Payment getPaymentById(int loanID){
        Session session = getSession();
        Payment payment = session.get(Payment.class, loanID);
        session.close();
        return payment;
    }

    public boolean deletePayment(int loanID){
        boolean deleteStatus = false;
        try{
           Payment payment = getPaymentById(loanID);
           Session session = getSession();
           session.beginTransaction();
           session.delete(payment);
           session.getTransaction().commit();;
           session.close();
           deleteStatus = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return deleteStatus;
    }

    public void updateApprovedBy(int loanID, String user){
        try {
            Session session = getSession();
            session.beginTransaction();
            Payment payment = session.get(Payment.class, loanID);
            payment.setReviewedBy(user);
            session.update(payment);
            session.getTransaction().commit();
            session.close();;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

}
