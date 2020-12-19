package com.nucleus.repaymentpolicy.dao;

import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RepaymentPolicyDaoImpl implements RepaymentPolicyDao{

    @Autowired
    private SessionFactory sessionFactory;

    public List<RepaymentPolicy> getRepaymentPolicyList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<RepaymentPolicy> query = session.createQuery("FROM  RepaymentPolicy");
        List<RepaymentPolicy> list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    /**
     * Returns a object of given Repayment Policy
     * @param policyCode Repayment Policy Id
     * @return object of given Repayment Policy
     */
    public RepaymentPolicy getRepaymentPolicyById(String policyCode) {
        Session session = sessionFactory.openSession();
        RepaymentPolicy repaymentPolicy = session.get(RepaymentPolicy.class, policyCode);
        session.close();
        return repaymentPolicy;
    }

    public String addRepaymentPolicy(RepaymentPolicy repaymentPolicy) {
        Session session = sessionFactory.getCurrentSession();
        String id = (String) session.save(repaymentPolicy);
        return id;
    }


    public boolean deleteRepaymentPolicy(String id) {
        boolean successful = false;
        try
        {
            RepaymentPolicy repaymentPolicy =  getRepaymentPolicyById(id);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(repaymentPolicy);
            session.getTransaction().commit();
            session.close();
            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public boolean updateRepaymentPolicy(RepaymentPolicy repaymentPolicy) {
        boolean successful = false;
        try
        {
            Session session = sessionFactory.getCurrentSession(); //same session managed by context - Spring context

            RepaymentPolicy existingPolicy = session.load(RepaymentPolicy.class, repaymentPolicy.getPolicyCode());

            existingPolicy.setPolicyCode(repaymentPolicy.getPolicyCode());
            existingPolicy.setPolicyName(repaymentPolicy.getPolicyName());
            existingPolicy.setPolicyDescription(repaymentPolicy.getPolicyDescription());
            existingPolicy.setDate(repaymentPolicy.getDate());
            existingPolicy.setRepaymentFrequency(repaymentPolicy.getRepaymentFrequency());
            existingPolicy.setMinTenure(repaymentPolicy.getMinTenure());
            existingPolicy.setMaxTenure(repaymentPolicy.getMaxTenure());
            existingPolicy.setDefaultTenure(repaymentPolicy.getDefaultTenure());
            existingPolicy.setInterestRateType(repaymentPolicy.getInterestRateType());
            existingPolicy.setDefaultRate(repaymentPolicy.getDefaultRate());
            existingPolicy.setModifiedDate(repaymentPolicy.getModifiedDate());
            existingPolicy.setModifiedBy(repaymentPolicy.getModifiedBy());
            existingPolicy.setAuthorizedDate(repaymentPolicy.getAuthorizedDate());
            existingPolicy.setAuthorizedBy(repaymentPolicy.getAuthorizedBy());

            session.update(existingPolicy);

            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }

    public void changeStatus(String policyCode, String status) {
        Session session = sessionFactory.getCurrentSession();
        RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
        existingPolicy.setStatus(status);
        session.update(existingPolicy);
    }


    public void updateCreationParameters(String policyCode, String name) {
        Session session = sessionFactory.getCurrentSession();
        RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
        existingPolicy.setCreatedBy(name);
        existingPolicy.setCreatedDate(LocalDate.now());
        session.update(existingPolicy);
    }


    public void updateModificationParameters(String policyCode, String name) {
        Session session = sessionFactory.getCurrentSession();
        RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
        existingPolicy.setModifiedBy(name);
        existingPolicy.setModifiedDate(LocalDate.now());
        session.update(existingPolicy);
    }


    public void updateAuthorizationParameters(String policyCode, String name) {
        Session session = sessionFactory.getCurrentSession();
        RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
        existingPolicy.setAuthorizedBy(name);
        existingPolicy.setAuthorizedDate(LocalDate.now());
        session.update(existingPolicy);
    }

}
