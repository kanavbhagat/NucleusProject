package com.nucleus.repaymentschedule.dao;

import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository

public class RepaymentScheduleDAOImpl implements RepaymentScheduleDAO{

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
    public int addRepaymentSchedule(RepaymentSchedule repaymentSchedule) {

        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(repaymentSchedule);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
