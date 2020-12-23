package com.nucleus.charge.dao;

import com.nucleus.charge.model.NewCharge;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
@PropertySource("classpath:status.properties")
public class ChargeDaoImpl implements ChargeDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${status.pending}")
    private String pending;

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
    public boolean insert(NewCharge charge, String status) {
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                charge.setCreateDate(LocalDate.now());
                charge.setStatus(status);
                LoginDetailsImpl loginDetails = new LoginDetailsImpl();
                charge.setCreatedBy(loginDetails.getUserName());
                session.save(charge);
                session.getTransaction().commit();
                return true;
            } catch (Exception e){
                System.out.println("*************************************");
                System.out.println(e.getMessage());
                System.out.println("*************************************");
                session.getTransaction().rollback();
                return false;
            }
        }
    }

    @Override
    public List<NewCharge> getChargeList() {
        List<NewCharge> chargeList;
        try {
            Session session = getSession();
            session.beginTransaction();
            chargeList = session.createQuery("from NewCharge",NewCharge.class).getResultList();
            session.getTransaction().commit();
        }catch (Exception e) {
            chargeList = null;
        }
        return chargeList;
    }

    @Override
    public List<NewCharge> getPendingChargeList() {
        List<NewCharge> chargeList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<NewCharge> query = session.createQuery("from NewCharge c where c.status=?1",NewCharge.class);
            query.setParameter(1,pending);
            chargeList = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e) {
            chargeList = null;
        }
        return chargeList;
    }

    @Override
    public NewCharge getOneCharge(String chargeCode) {
        NewCharge charge;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<NewCharge> query = session.createQuery("from NewCharge c where c.chargeCode=?1", NewCharge.class);
            query.setParameter(1, chargeCode);
            charge = query.getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            charge = null;
            exception.printStackTrace();
        }
        return charge;
    }

    @Override
    public boolean deleteCharge(String policyCode) {
        boolean deleteStatus;
        NewCharge charge = getOneCharge(policyCode);
        try{
            Session session = getSession();
            session.beginTransaction();
            session.delete(charge);
            session.getTransaction().commit();
            deleteStatus = true;
            session.close();
        } catch (Exception exception) {
            deleteStatus = false;
            exception.printStackTrace();
        }
        return deleteStatus;
    }

    @Override
    public boolean updateCharge(NewCharge charge) {
        try(Session session = getSession()) {
            session.beginTransaction();
            try {
                NewCharge oldCharge = session.get(NewCharge.class, charge.getChargeCode());
                oldCharge.setChargeDescription(charge.getChargeDescription());
                oldCharge.setChargePaymentType(charge.getChargePaymentType());
                oldCharge.setTransactionEvent(charge.getTransactionEvent());
                oldCharge.setChargeType(charge.getChargeType());
                oldCharge.setChargeAmount(charge.getChargeAmount());
                oldCharge.setStatus(charge.getStatus());
                oldCharge.setModifiedDate(LocalDate.now());
                oldCharge.setModifiedBy(new LoginDetailsImpl().getUserName());
                session.saveOrUpdate(oldCharge);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }

    @Override
    public boolean updateStatus(String chargeCode, String status) {
        try(Session session = getSession()) {
            session.beginTransaction();
            try {
                NewCharge charge = session.get(NewCharge.class, chargeCode);
                charge.setStatus(status);
                charge.setAuthorizedDate(LocalDate.now());
                charge.setAuthorizedBy(new LoginDetailsImpl().getUserName());
                session.saveOrUpdate(charge);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }
}
