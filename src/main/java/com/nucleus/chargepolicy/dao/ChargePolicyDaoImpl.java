package com.nucleus.chargepolicy.dao;

import com.nucleus.chargepolicy.model.ChargePolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
/**
 * This class acts as a Database layer for all
 * Charge Policy related operations.
 *
 */
@Repository
public class ChargePolicyDaoImpl implements ChargePolicyDao{
    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E) {
            session = sessionFactory.openSession();
        }
        return session;
    }
    /**
     * This method inserts the entry to the database
     * @param chargePolicy This is the model which is to be inserted in the database
     * @return It returns an integer which can take three values based on the status of insertion.
     * status - 1 : Successfully inserted to the database
     * status - 2 : Either the chargePolicy sent is null or any error apart from duplicate ID error.
     * status - 3 : Duplicate ID(Primary Key) which is chargePolicyCode in this case.
     */
    public int insert(ChargePolicy chargePolicy) {
        int insertStatus;
        if(chargePolicy==null)return 2;
        LocalDate todayDate = LocalDate.now();
        Session session = getSession();
        try{
            session.beginTransaction();
            chargePolicy.setCreatedDate(todayDate);
            session.save(chargePolicy);
            session.getTransaction().commit();
            insertStatus = 1;session.close();

        } catch (Exception exception) {
            exception.printStackTrace();
            if(exception.getMessage().contains("ConstraintViolationException"))insertStatus = 3;
            else {
                insertStatus = 2;
            }
            session.close();
        }
        finally {
            session.close();
        }
        return insertStatus;
    }

    /**
     * @return Returns the entire list of charge policies in the database.
     */
    public List<ChargePolicy> getPolicyList(){
        List<ChargePolicy> chargePolicyList;
        Session session = getSession()  ;
        try {
            session.beginTransaction();
            Query<ChargePolicy> query = session.createQuery("from ChargePolicy",ChargePolicy.class);
            chargePolicyList = query.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            chargePolicyList = null;
            session.close();
        }
        finally {
            session.close();
        }
        return chargePolicyList;
    }

    /**
     *
     * @param chargePolicyCode This is the charge policy code of the Charge Policy to be retrieved.
     * @return Returns Charge Policy corresponding to the policy code
     */

    public ChargePolicy getChargePolicy(String chargePolicyCode){
        ChargePolicy chargePolicy;
        Session session = getSession();
        try{
            session.beginTransaction();
            Query<ChargePolicy> query = session.createQuery("from ChargePolicy cp where cp.chargePolicyCode = '"+chargePolicyCode+"'",ChargePolicy.class);
            chargePolicy= query.getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            chargePolicy = null;
            session.close();
        }
        finally {
            session.close();
        }
        return chargePolicy;

    }

    /**
     * This method updates the status of the Charge Policy.
     * @param chargePolicyCode Policy Code of the policy whose status needs to be changed
     * @param status this is status of the charge policy currently as sent by the user
     * @param approvedBy name of the checker who approved this Charge Policy
     * @return true when update is successful, false otherwise.
     */
    public boolean updateStatus(String chargePolicyCode,String status,String approvedBy){
        boolean returnstatus = false;
        Session session = getSession();
        try {
            session.beginTransaction();
            Query q=session.createQuery("update ChargePolicy set authorized_date=:date,status=:status,authorized_by=:user where policy_code=:policyCode");
            q.setParameter("date",LocalDate.now());
            q.setParameter("status",status);
            q.setParameter("user",approvedBy);
            q.setParameter("policyCode",chargePolicyCode);
            int s=q.executeUpdate();
            if(s==1)returnstatus = true;
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            exception.printStackTrace();
            session.close();
        }
        finally {
            session.close();
        }
        return returnstatus;
    }

    /**
     * This method sends the updated Charge Policy to the database.
     * @param chargePolicy This is the updated model whose ID remains unchanged
     * @return true when update is successful, false otherwise.
     */

    public boolean updateEntry(ChargePolicy chargePolicy){
        boolean status ;
        Session session = getSession();
        try{
            session.beginTransaction();
            session.update(chargePolicy);
            status = true;
            session.getTransaction().commit();
            session.close();

        } catch (Exception exception) {
           status = false;
           session.close();
        }
        finally {
            session.close();
        }
        return status;
    }

    /**
     * This method deletes the entry in the database
     * @param chargePolicyCode Policy Code of the Charge Policy to be deleted
     * @return 0 if there is no entry corresponding to the following code. 1, if successfully deleted.
     */
    public int deleteChargePolicy(String chargePolicyCode){
        int s = 0;
        Session session = getSession();
        try {
            session.beginTransaction();
            Query q=session.createQuery("delete from ChargePolicy where chargePolicyCode= :chargePolicyCode");
            q.setParameter("chargePolicyCode", chargePolicyCode);
            s=q.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            exception.printStackTrace();
            session.close();
        }
        finally {
            session.close();
        }
        return s;
    }
}
