package com.nucleus.customerservice.repaymentschedulereport.dao;

import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepaymentScheduleReportDAOImpl implements RepaymentScheduleReportDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public List<RepaymentSchedule> getRepaymentScheduleReport(int loanApplicationNumber) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        Query query = session.createQuery("from RepaymentSchedule where loanApplicationNumber = :code ");
//        query.setParameter("code", loanApplicationNumber);
//        List<RepaymentSchedule> rslist = query.getResultList();
        List<RepaymentSchedule> rslist = session.createCriteria(RepaymentSchedule.class)
                                                .createAlias("loanApplicationNumber","lpn")
                                                .add(Restrictions.eq("lpn.loanApplicationNumber", loanApplicationNumber ))
                                                .list();
        session.getTransaction().commit();
        session.close();
        return rslist;
    }
}
