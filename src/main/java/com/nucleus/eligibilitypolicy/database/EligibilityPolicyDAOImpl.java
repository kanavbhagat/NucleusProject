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

    private void closeSessionFactory() {
        sessionFactory.close();
    }

    @Override
    public List<EligibilityPolicy> getAllEligibilityPolicies() {
        return null;
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
        closeSessionFactory();
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
            Query<EligibilityParameter> query = session.createQuery("from ELIGIBILITY_PARAMETERS e", EligibilityParameter.class);
            eligibilityParameterList = query.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            eligibilityParameterList = null;
        }
        closeSessionFactory();
        return eligibilityParameterList;
    }
}
