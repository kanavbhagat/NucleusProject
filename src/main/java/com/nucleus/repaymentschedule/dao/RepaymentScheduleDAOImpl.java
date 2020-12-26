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

/**
 * This class acts as a Database layer for RepaymentSchedule related operations.
 *
 */
@Repository
@Transactional
public class RepaymentScheduleDAOImpl implements RepaymentScheduleDAO
{

    @Autowired
    private SessionFactory sessionFactory;



    /**
     * Adds a Repayment Schedule to the database.
     * @param repaymentSchedule is the RepaymentSchedule to be saved.
     * @return returns 1 if successfully added else 0.
     */
    @Override
    public int addRepaymentSchedule(RepaymentSchedule repaymentSchedule) {

        int r=1;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(repaymentSchedule);
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            r=0;
            e.printStackTrace();
        }
        return r;
    }
}
