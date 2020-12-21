package com.nucleus.receipt.dao;

import com.nucleus.receipt.model.Receipt;
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
            return receiptList;
        }
    }
}
