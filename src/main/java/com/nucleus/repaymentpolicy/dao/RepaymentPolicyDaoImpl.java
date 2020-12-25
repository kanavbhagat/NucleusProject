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

/**
 * This class acts as a Database layer for all RepaymentPolicy related operations.
 *
 */
@Repository
@Transactional
public class RepaymentPolicyDaoImpl implements RepaymentPolicyDao{

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Retrieves all the Repayment Policies existing in the database.
     * @return List of all repayment policies in the database.
     */
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
     * Retrieves a Repayment Policy by its policyCode from the database.
     * @param policyCode is the policyCode String of the Repayment Policy to be retrieved.
     * @return object of given Repayment Policy if exists, else null.
     */
    public RepaymentPolicy getRepaymentPolicyById(String policyCode) {
        try {
            Session session = sessionFactory.openSession();
            RepaymentPolicy repaymentPolicy = session.get(RepaymentPolicy.class, policyCode);
            session.close();
            return repaymentPolicy;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Adds a Repayment Policy to the database.
     * @param repaymentPolicy is the RepaymentPolicy to be saved.
     * @return Boolean true if creation was successful, else false.
     */
    public boolean addRepaymentPolicy(RepaymentPolicy repaymentPolicy){

        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            try {
                session.save(repaymentPolicy);
                session.getTransaction().commit();
                session.close();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                session.close();
                return false;
            }
        }
    }

    /**
     * Deletes a Repayment Policy from the database.
     * @param id is the policyCode String of the RepaymentPolicy to be deleted.
     * @return Boolean true if deletion was successful, else false.
     */
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

    /**
     * Updates a Repayment Policy in the database.
     * @param repaymentPolicy repaymentPolicy is the RepaymentPolicy which will replace the existing one with same policyCode
     * @return Boolean true if update was successful, else false.
     */
    @Transactional(propagation= Propagation.MANDATORY)
    public boolean updateRepaymentPolicy(RepaymentPolicy repaymentPolicy) {

        if(checkNameExists(repaymentPolicy.getPolicyCode(),repaymentPolicy.getPolicyName()))
        {
            return false;
        }

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

    /**
     * Changes the status of a RepaymentPolicy. (SAVED, PENDING, APPROVED, REJECTED)
     * @param policyCode is policyCode String of RepaymentPolicy to which the status change is applied.
     * @param status is the new status String given to the chosen RepaymentPolicy.
     * @return Boolean true if update was successful, else false.
     */
    public boolean changeStatus(String policyCode, String status) {
        boolean success=false;
        try {
            Session session = sessionFactory.getCurrentSession();
            RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
            existingPolicy.setStatus(status);
            session.update(existingPolicy);
            success=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return success;

    }

    /**
     * Adds the name of a RepaymentPolicy Creator and the date of Creation.
     * @param policyCode is policyCode String of RepaymentPolicy to which the Creation parameters are added.
     * @param name is the String of Creator name.
     * @return Boolean true if update was successful, else false.
     */
    public boolean updateCreationParameters(String policyCode, String name) {
        boolean success=false;
        try {
            Session session = sessionFactory.getCurrentSession();
            RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
            existingPolicy.setCreatedBy(name);
            existingPolicy.setCreatedDate(LocalDate.now());
            session.update(existingPolicy);
            success=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Updates the name of a RepaymentPolicy Modifier and the date of Modification.
     * @param policyCode is policyCode String of RepaymentPolicy of which the Modification parameters are updated.
     * @param name is the String of Modifier name.
     * @return Boolean true if update was successful, else false.
     */
    public boolean updateModificationParameters(String policyCode, String name) {
        boolean success=false;
        try {
            Session session = sessionFactory.getCurrentSession();
            RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
            existingPolicy.setModifiedBy(name);
            existingPolicy.setModifiedDate(LocalDate.now());
            session.update(existingPolicy);
            success=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Updates the name of a RepaymentPolicy Authorizer and the date of Authorization.
     * @param policyCode is policyCode String of RepaymentPolicy of which the Authorization parameters are updated.
     * @param name is the String of Authorizer name.
     * @return Boolean true if update was successful, else false.
     */
    public boolean updateAuthorizationParameters(String policyCode, String name) {
        boolean success=false;
        try {
            Session session = sessionFactory.getCurrentSession();
            RepaymentPolicy existingPolicy = session.get(RepaymentPolicy.class, policyCode);
            existingPolicy.setAuthorizedBy(name);
            existingPolicy.setAuthorizedDate(LocalDate.now());
            session.update(existingPolicy);
            success=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Checks for duplicity of policyName for another Policy (used in updating Policy)
     *
     * @return Boolean true if duplicity exists, else false.
     */
    boolean checkNameExists(String policyCode,String policyName)
    {
        List<RepaymentPolicy>list=getRepaymentPolicyList();
        if(list.isEmpty())
        {
            return true;
        }
        for (RepaymentPolicy repaymentPolicy : list) {
            if (repaymentPolicy.getPolicyName().equals(policyName) && !repaymentPolicy.getPolicyCode().equals(policyCode)) {
                return true;
            }
        }
        return false;
    }

}
