package com.nucleus.eligibilitypolicy.database;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EligibilityPolicyDAOImpl implements EligibilityPolicyDAO {
    @Autowired
    private SessionFactory sessionFactory;

    //Get an object of Session class:
    private Session getSession(){
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E){
            session = sessionFactory.openSession();
        }
        return session;
    }

    //To get a list of all Eligibility Policies:
    @Override
    public List<EligibilityPolicy> getAllEligibilityPolicies() {
        List<EligibilityPolicy> eligibilityPolicyList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<EligibilityPolicy> query = session.createQuery("from EligibilityPolicy e", EligibilityPolicy.class);
            eligibilityPolicyList = query.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            eligibilityPolicyList = null;
            exception.printStackTrace();
        }
        return eligibilityPolicyList;

    }

    //To add a new Eligibility Policy to database:
    @Override
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        boolean insertStatus;
        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(eligibilityPolicy);
            session.getTransaction().commit();
            insertStatus = true;
            session.close();
        } catch (Exception exception) {
            insertStatus = false;
            exception.printStackTrace();
        }
        return insertStatus;
    }

    //To retrieve one Eligibility Policy by Policy Code:
    @Override
    public EligibilityPolicy getOneEligibilityPolicy(String policyCode) {
        EligibilityPolicy eligibilityPolicy;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<EligibilityPolicy> query = session.createQuery("from EligibilityPolicy e where e.policyCode=?1", EligibilityPolicy.class);
            query.setParameter(1, policyCode);
            eligibilityPolicy = query.getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            eligibilityPolicy = null;
            exception.printStackTrace();
        }
        return eligibilityPolicy;
    }

    //To update an existing Eligibility Policy:
    @Override
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        boolean updateStatus;
        try{
            Session session = getSession();
            session.beginTransaction();
            session.update(eligibilityPolicy);
            session.getTransaction().commit();
            updateStatus = true;
            session.close();
        } catch (Exception exception) {
            updateStatus = false;
            exception.printStackTrace();
        }
        return updateStatus;
    }

    //To delete an existing Eligibility Policy:
    @Override
    public boolean deleteEligibilityPolicy(String policyCode) {
        boolean deleteStatus;
        EligibilityPolicy eligibilityPolicy = getOneEligibilityPolicy(policyCode);
        try{
            Session session = getSession();
            session.beginTransaction();
            session.delete(eligibilityPolicy);
            session.getTransaction().commit();
            deleteStatus = true;
            session.close();
        } catch (Exception exception) {
            deleteStatus = false;
            exception.printStackTrace();
        }
        return deleteStatus;
    }

}
