package com.nucleus.receipt.dao;

import com.nucleus.product.model.Product;
import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceiptDAO implements ReceiptDAOInterface{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception E){
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
            } catch (Exception e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }

    @Override
    public Boolean updateReceipt(Receipt receipt) {

        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.update(receipt);
                session.getTransaction().commit();
                return true;
            } catch (Exception e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }

    @Override
    public Receipt getReceipt(Integer receiptId) {
        Receipt receipt;
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                receipt = session.get(Receipt.class, receiptId);
                session.getTransaction().commit();
                return receipt;
            } catch (Exception e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return null;
            }
        }
    }

    public List<Object> receiptSearch(String receiptType, String receiptBasis, Integer accountNumber, Integer receiptNo){
        List<Object> receiptList;
        try(Session session = getSession()) {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Receipt.class);
            criteria.add(Restrictions.eq("receiptType", receiptType));
            if(!"-".equals(receiptBasis) && receiptBasis!=null){
                criteria.add(Restrictions.eq("receiptBasis", receiptBasis));
            }
            if(receiptNo!=null){
                criteria.add(Restrictions.eq("receiptNo", receiptNo));
            }
            if(accountNumber!=null){
                criteria.add(Restrictions.eq("loanApplicationNumber.loanApplicationNumber", accountNumber));
            }
            receiptList = criteria.list();
            session.getTransaction().commit();
            return receiptList;
        } catch (Exception exception){
            exception.printStackTrace();
            receiptList = new ArrayList<>();
            System.out.println("it come here. ono");
            return receiptList;
        }
    }

    @Override
    public List<Receipt> getReceiptList() {
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<Receipt> query = session.createQuery("from Receipt r");
            List<Receipt> receiptList = query.list();
            session.getTransaction().commit();
            return receiptList;
        }
    }

    public Boolean runBOD(Receipt receipt, Advice advice, Settlement settlement){
        try(Session session = getSession()) {
            session.beginTransaction();
            try {
                session.update(receipt);
                session.save(advice);
                session.save(settlement);
                session.getTransaction().commit();
                return true;
            }catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }
}
