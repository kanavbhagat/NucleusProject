package com.nucleus.eligibilitypolicy.database;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class acts as a Database layer for all
 * Eligibility Policy related operations.
 *
 */
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

    /**
     * This method is used to get a list of all Eligibility Policies.
     *
     * @return List This returns a list of all policies in the database.
     */
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
            System.out.println(exception.getMessage());
        }
        return eligibilityPolicyList;

    }

    /**
     * This method is used to add a new Eligibility Policy to database.
     *
     * @param eligibilityPolicy This is the model that has to be added to the database.
     *
     * @return boolean This returns a true/false based on whether the object was successfully added or not.
     */
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
            System.out.println(exception.getMessage());
        }
        return insertStatus;
    }

    /**
     * This method is used to retrieve one Eligibility Policy by Policy Code.
     *
     * @param policyCode This contains the policyCode
     *                   for which Eligibility Policy is to be fetched.
     *
     * @return EligibilityPolicy This returns the EligibilityPolicy that was required.
     */
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
            System.out.println(exception.getMessage());
        }
        return eligibilityPolicy;
    }

    /**
     * This method is used to update an existing Eligibility Policy (all fields).
     *
     * @param eligibilityPolicy This is the new Eligibility Policy
     *                          that has to be inserted in place of the old one.
     *
     * @return boolean This returns a true/false based on whether the policy was successfully updated or not.
     */
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
            System.out.println(exception.getMessage());
        }
        return updateStatus;
    }

    /**
     * This method is used to delete an existing Eligibility Policy.
     *
     * @param policyCode This contains the policyCode of the
     *                   Eligibility Policy that is to be deleted.
     *
     * @return boolean This returns a true/false based on whether the policy was successfully deleted or not.
     */
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
            System.out.println(exception.getMessage());
        }
        return deleteStatus;
    }

}
