package com.nucleus.receipt.dao;

import com.nucleus.receipt.model.Receipt;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReceiptDAO implements ReceiptDAOInterface{

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
    public Boolean createNewReceipt(Receipt receipt) {

        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.save(receipt);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
        //return null;
    }
    @Override
    public List<Receipt> getReceiptList(String rtype, String rBasis, String accountNo, String rRef) {
        List<Receipt> receiptList;
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<Receipt> query = session.createQuery("from receipts where loan_application_number = :accountNo");
            receiptList = query.list();
            session.getTransaction().commit();
            return receiptList;
        }catch(Exception exception) {
            return null;
        }

    }
}
