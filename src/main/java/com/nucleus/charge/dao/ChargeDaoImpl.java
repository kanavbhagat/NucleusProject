package com.nucleus.charge.dao;

import com.nucleus.charge.model.NewCharge;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ChargeDaoImpl implements ChargeDao{
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
    public boolean insert(NewCharge charge) {
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                charge.setCreateDate(LocalDate.now());
                charge.setStatus("Waiting for Approval");
                session.save(charge);
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
    public List<NewCharge> getChargeList() {
        List<NewCharge> chargeList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query query = session.createQuery("from NewCharge",NewCharge.class);
            chargeList = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            chargeList = null;
        }
        return chargeList;
    }
}
