package com.nucleus.repaymentpolicy.dao;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RepaymentPolicyDaoImpl implements RepaymentPolicyDao{

    @Autowired
    private SessionFactory sessionFactory;

    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(repaymentPolicy);
        tx.commit();
        session.close();
        System.out.println("adding");
        return true;
    }

    public List<RepaymentPolicy> getRepaymentPolicyList()
    {
        Session session = sessionFactory.getCurrentSession();
        Query<RepaymentPolicy> query = session.createQuery("FROM RepaymentPolicy");
        List<RepaymentPolicy> list = query.getResultList();
        return list;
    }

    @Override
    public boolean deleteRepaymentPolicy(String repaymentPolicyId) {
        return false;
    }

    @Override
    public boolean updateRepaymentPolicy(String repaymentPolicyId) {
        return false;
    }

    @Override
    public boolean getRepaymentPolicyById(String repaymentPolicyId) {
        return false;
    }
}
