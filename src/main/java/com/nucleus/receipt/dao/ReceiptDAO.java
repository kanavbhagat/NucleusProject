package com.nucleus.receipt.dao;

import com.nucleus.receipt.model.Advice;
import com.nucleus.receipt.model.Receipt;
import com.nucleus.receipt.model.Settlement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * <p> Class handling all data operations for receipts. </p>
 */
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


    /**
     * <p> creates a new receipt in the database. returns true if successful, else false. </p>
     * @param receipt the receipt object to be saved
     * @return true if operation was successful, or false.
     */
    @Override
    public Boolean createNewReceipt(Receipt receipt) {

        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.save(receipt);
                session.getTransaction().commit();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return false;
            }
        }
    }


    /**
     * <p> updates a receipt in the database. returns true if successful, else false. </p>
     * @param receipt the receipt object to be updated
     * @return true if operation was successful, or false.
     */
    @Override
    public Boolean updateReceipt(Receipt receipt) {

        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.update(receipt);
                session.getTransaction().commit();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return false;
            }
        }
    }


    /**
     * <p> retrieves a receipt by id from the database. returns the receipt if succssful, else null. </p>
     * @param receiptId receipt Id of the receipt to be retrieved
     * @return retrieved receipt object if successful, else null.
     */
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
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return null;
            }
        }
    }


    /**
     * <p> Searches for a receipt in the database based on four parameters </p>
     * @param receiptType of the receipt object to be found
     * @param receiptBasis of the receipt object to be found
     * @param accountNumber of the receipt object to be found
     * @param receiptNo of the receipt object to be found
     * @return List with matching receipt objects, else an empty list.
     */
    public List<Receipt> receiptSearch(String receiptType, String receiptBasis, Integer accountNumber, Integer receiptNo){
        List<Receipt> receiptList;
        try(Session session = getSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Receipt> cr = cb.createQuery(Receipt.class);
            Root<Receipt> root = cr.from(Receipt.class);
            ArrayList<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("receiptType"), receiptType));
            if(!"-".equals(receiptBasis) && receiptBasis!=null){
                predicates.add(cb.equal(root.get("receiptBasis"), receiptBasis));
            }
            if(receiptNo!=null){
                predicates.add(cb.equal(root.get("receiptNo"), receiptNo));
            }
            if(accountNumber!=null){
                predicates.add(cb.equal(root.get("loanApplicationNumber"), accountNumber));
            }
            Predicate[] predicateList = new Predicate[predicates.size()];
            predicateList = predicates.toArray(predicateList);
            cr.select(root).where(predicateList);
            Query<Receipt> query = session.createQuery(cr);
            receiptList = query.getResultList();
            session.getTransaction().commit();
            return receiptList;
        } catch (Exception exception){
            System.out.println(exception.getMessage());
            receiptList = new ArrayList<>();
            return receiptList;
        }
    }


    /**
     * <p> retrieves a list of all the receipts from the database. returns a list of receipts. </p>
     * @return list of all receipts, or empty list if no receipts found.
     */
    @Override
    public List<Receipt> getReceiptList() {
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<Receipt> query = session.createQuery("from Receipt r", Receipt.class);
            List<Receipt> receiptList = query.list();
            session.getTransaction().commit();
            return receiptList;
        }
    }


    /**
     * <p> Runs the BOD process by updating the receipt object, and adding the advice and settlement objects. </p>
     * @param receipt receipt object to be udpated
     * @param advice advice object to be created.
     * @param settlement settlement object to be created.
     * @return retrieved true if all operations were successful, else false.
     */
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
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return false;
            }
        }
    }
}
