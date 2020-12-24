package com.nucleus.loanclosurebod.database;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.repaymentschedule.model.RepaymentSchedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the functions declared by the LoanClosureDao
 * Interface.
 */
@Repository
public class LoanClosureDaoImpl implements LoanClosureDao{

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Method for creating a session and returning an object for
     * using session functionalities to other class methods.
     * @return Session
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
     * This method retrieves the list of all Loan Applications.
     * @return list of all applied loans.
     */
    @Override
    public List<LoanApplications> getLoanApplications(){
        List<LoanApplications> loanApplications = new ArrayList<>();
        try(Session session = getSession()){
            session.beginTransaction();
            Query<LoanApplications> query = session.createQuery("from LoanApplications");
            loanApplications = query.getResultList();
            session.getTransaction().commit();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return loanApplications;
    }

    /**
     * This method retrieves the list of all entries of Repayment Schedule
     * for a particular Loan Application from the database.
     * @param loanApplication
     * @return list of all entries of Repayment Schedule
     */
    @Override
    public List<RepaymentSchedule> getRepaymentSchedule(LoanApplications loanApplication){
        List<RepaymentSchedule> list = new ArrayList<>();
        /* Retrieving all the entries of Repayment Schedule for given Loan Application
           in the list. */
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<RepaymentSchedule> query = session.createQuery("from RepaymentSchedule r where r.loanApplicationNumber=?1", RepaymentSchedule.class);
            query.setParameter(1, loanApplication);
            list = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return list;
    }

    /**
     * This method updates the status of Loan Application in the database
     * depending on whether the loan application is eligible for closing or not.
     * @param loanApplication
     * @param newStatus
     * @return updateStatus
     */
    @Override
    public boolean updateStatus(LoanApplications loanApplication, String newStatus){
        boolean updateStatus;
        /* Updating the status of the Loan Application with newStatus. */
        try(Session session = getSession()){
            session.beginTransaction();
            loanApplication.setStatus(newStatus);
            session.update(loanApplication);
            session.getTransaction().commit();
            updateStatus = true;
        }catch (Exception exception) {
            updateStatus = false;
            exception.printStackTrace();
        }
        return updateStatus;
    }
}