package com.nucleus.eligibilitypolicy.database;

import com.nucleus.eligibilitypolicy.model.EligibilityParameter;
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
    public List<EligibilityPolicy> getAllEligibilityPolicies() {
        List<EligibilityPolicy> eligibilityPolicyList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<EligibilityPolicy> query = session.createQuery("from EligibilityPolicy", EligibilityPolicy.class);
            eligibilityPolicyList = query.getResultList();
            session.getTransaction().commit();
        } catch(Exception exception) {
            eligibilityPolicyList = null;
        }
        return eligibilityPolicyList;

    }

    @Override
    public boolean insertEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        boolean insertStatus;
        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(eligibilityPolicy);
            session.getTransaction().commit();
            insertStatus = true;
        } catch (Exception exception) {
            insertStatus = false;
        }
        return insertStatus;
    }

    @Override
    public boolean updateEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        return false;
    }

    @Override
    public boolean deleteEligibilityPolicy(EligibilityPolicy eligibilityPolicy) {
        return false;
    }

    @Override
    public List<EligibilityParameter> getParameters() {
        List<EligibilityParameter> eligibilityParameterList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<EligibilityParameter> query = session.createQuery("from EligibilityParameter", EligibilityParameter.class);
            eligibilityParameterList = query.getResultList();
            session.getTransaction().commit();
        } catch(Exception exception) {
            eligibilityParameterList = null;
        }
        return eligibilityParameterList;
    }

    @Override
    public void addParameters() {
        Session session = getSession();
        session.beginTransaction();

        EligibilityParameter eligibilityParameter1 = new EligibilityParameter();
        eligibilityParameter1.setParameterName("Test 1");
        eligibilityParameter1.setParameterDescription("Testing 1");
        eligibilityParameter1.setParameterCode("101");
        session.save(eligibilityParameter1);

        EligibilityParameter eligibilityParameter2 = new EligibilityParameter();
        eligibilityParameter2.setParameterName("Test 2");
        eligibilityParameter2.setParameterDescription("Testing 2");
        eligibilityParameter2.setParameterCode("102");
        session.save(eligibilityParameter2);
        session.getTransaction().commit();
    }
}
