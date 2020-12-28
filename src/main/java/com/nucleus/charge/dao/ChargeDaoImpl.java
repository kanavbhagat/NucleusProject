package com.nucleus.charge.dao;

import com.nucleus.charge.model.NewCharge;
import com.nucleus.login.logindetails.LoginDetailsImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * This class acts as a Database layer for all
 * Charge related operations.
 *
 */
@Repository
@PropertySource("classpath:status.properties")
public class ChargeDaoImpl implements ChargeDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${status.pending}")
    private String pending;

    /**
     *
     * @return Session This method returns an object of Session class
     */
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
     * This method is used to add a new Charge to database.
     *
     * @param charge This is the model that has to be added to the database.
     * @param status This is the status of charge(i.e., SAVED/PENDING) object added to database.
     *
     * @return int This returns a status based on whether the object was successfully added or not.
     * status - 0 : Any error apart from duplicate ID  or Name error.
     * status - 1 : Successfully inserted to the database
     * status - 2 : Duplicate ID(Primary Key) which is chargeCode or Name(Unique Constraint) which is chargeCodeName in this case.
     *
     */
    @Override
    public int insert(NewCharge charge, String status) {
        int insertStatus = 0;
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                charge.setCreateDate(LocalDate.now());
                charge.setStatus(status);
                LoginDetailsImpl loginDetails = new LoginDetailsImpl();
                charge.setCreatedBy(loginDetails.getUserName());
                session.save(charge);
                session.getTransaction().commit();
                insertStatus = 1;
            } catch (Exception e){
                e.printStackTrace();
                if(e.getMessage().contains("ConstraintViolationException"))
                    insertStatus = 2;
                else
                    insertStatus = 0;
                session.getTransaction().rollback();
            }
        }
        return insertStatus;
    }

    /**
     * This method is used to get a list of all Charges.
     *
     * @return List This returns a list of all charges in the database.
     */
    @Override
    public List<NewCharge> getChargeList() {
        List<NewCharge> chargeList;
        try (Session session = getSession()) {
            session.beginTransaction();
            chargeList = session.createQuery("from NewCharge", NewCharge.class).getResultList();
            session.getTransaction().commit();
            session.close();
        }catch (Exception e) {
            chargeList = null;
        }
        return chargeList;
    }

    /**
     * This method is used to get a list of all Pending Charges.
     *
     * @return List This returns a list of all pending charges in the database.
     */
    @Override
    public List<NewCharge> getPendingChargeList() {
        List<NewCharge> chargeList;
        try (Session session = getSession()) {
            session.beginTransaction();
            Query<NewCharge> query = session.createQuery("from NewCharge c where c.status=?1", NewCharge.class);
            query.setParameter(1, pending);
            chargeList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            chargeList = null;
        }
        return chargeList;
    }

    /**
     * This method is used to retrieve one Charge by Charge Code.
     *
     * @param chargeCode This contains the chargeCode
     *                   for which Charge is to be fetched.
     *
     * @return NewCharge This returns the Charge that was required.
     */
    @Override
    public NewCharge getOneCharge(String chargeCode) {
        NewCharge charge;
        try (Session session = getSession()) {
            session.beginTransaction();
            Query<NewCharge> query = session.createQuery("from NewCharge c where c.chargeCode=?1", NewCharge.class);
            query.setParameter(1, chargeCode);
            charge = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception exception) {
            charge = null;
            exception.printStackTrace();
        }
        return charge;
    }

    /**
     * This method is used to delete an existing Charge.
     *
     * @param chargeCode This contains the chargeCode of the
     *                   Charge that is to be deleted.
     *
     * @return boolean This returns a true/false based on whether the charge was successfully deleted or not.
     */
    @Override
    public boolean deleteCharge(String chargeCode) {
        boolean deleteStatus;
        NewCharge charge = getOneCharge(chargeCode);
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(charge);
            session.getTransaction().commit();
            deleteStatus = true;
        } catch (Exception exception) {
            deleteStatus = false;
            exception.printStackTrace();
        }
        return deleteStatus;
    }

    /**
     * This method is used to edit an existing Charge.
     *
     * @param charge This is the edited charge
     *                          that has to be updated in database.
     *
     * @return boolean This returns a true/false based on whether the charge was successfully updated or not.
     */
    @Override
    public boolean updateCharge(NewCharge charge) {
        try(Session session = getSession()) {
            session.beginTransaction();
            try {
                NewCharge oldCharge = session.get(NewCharge.class, charge.getChargeCode());
                oldCharge.setChargeDescription(charge.getChargeDescription());
                oldCharge.setChargePaymentType(charge.getChargePaymentType());
                oldCharge.setTransactionEvent(charge.getTransactionEvent());
                oldCharge.setChargeType(charge.getChargeType());
                oldCharge.setChargeAmount(charge.getChargeAmount());
                oldCharge.setStatus(charge.getStatus());
                oldCharge.setModifiedDate(LocalDate.now());
                LoginDetailsImpl login = new LoginDetailsImpl();
                oldCharge.setModifiedBy(login.getUserName());
                session.saveOrUpdate(oldCharge);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }

    /**
     * This method is used to update status(i.e., approve/reject) an existing Charge.
     *
     * @param chargeCode This contains the chargeCode of the
     *                   Charge whose status is to be updated.
     * @param status THis contains status that needs to be set.
     *
     * @return boolean This returns a true/false based on whether the charge status was successfully updated or not.
     */
    @Override
    public boolean updateStatus(String chargeCode, String status) {
        try(Session session = getSession()) {
            session.beginTransaction();
            try {
                NewCharge charge = session.get(NewCharge.class, chargeCode);
                charge.setStatus(status);
                charge.setAuthorizedDate(LocalDate.now());
                LoginDetailsImpl login = new LoginDetailsImpl();
                charge.setAuthorizedBy(login.getUserName());
                session.saveOrUpdate(charge);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }
}
