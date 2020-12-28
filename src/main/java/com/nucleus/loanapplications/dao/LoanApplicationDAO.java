package com.nucleus.loanapplications.dao;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.product.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class acts as a Database layer for all
 * Loan Application related operations.
 *
 */
@Repository
public class LoanApplicationDAO implements LoanApplicationDaoInterface {

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

    /**
     * This method is used to add Loan Application details to database.
     *
     * @param loanApplications This contains the object of
     *                         LoanApplication class
     *
     * @return boolean This returns a true/false based on whether the Application
     *          was successfully added or not.
     */

    @Override
    public boolean addApplication(LoanApplications loanApplications) {

        try(Session session = sessionFactory.openSession())
        {
            session.beginTransaction();
            try {
                session.save(loanApplications);
                session.getTransaction().commit();
                session.close();
                return true;
            } catch (Exception e){
                e.printStackTrace();
                session.getTransaction().rollback();
                session.close();
                return false;
            }
        }
    }


    /**
     * This method is used to get list of Loan Applications and their details from database.
     *
     * @return LoanApplications This returns list of objects of LoanApplications class with the details of
     *         all the Loan Applications.
     */
    public List<LoanApplications> getLoanApplicationList(){
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<LoanApplications> query = session.createQuery("from LoanApplications");
            List<LoanApplications> loanApplicationsList = query.list();
            session.getTransaction().commit();
            return loanApplicationsList;
        }

    }

    /**
     * This method is used to get Application details from database.
     *
     * @param id This contains the ID of Loan Application
     *           to retrieve its details from database
     *
     * @return LoanApplications This returns list of objects of Loan Applications
     *          class with the details of all loan applications.
     */
    public LoanApplications getLoanApplicationId(Integer id){
        try(Session session = getSession()){
            LoanApplications loanApplications;
            session.beginTransaction();
            try {
                loanApplications = session.get(LoanApplications.class, id);
                session.getTransaction().commit();
                return loanApplications;
            } catch (Exception e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return null;
            }
        }
    }

    /**
     * This method is used to REMOVE Loan Application from database.
     *
     * @param id This contains the ID of Loan Application
     *                 to be removed
     *
     * @return Boolean This returns a true/false based on whether the Removal
     *                 was successful or not.
     */
    @Override
    public boolean deleteLoanApplication(Integer id) {
        boolean successful = false;
        try
        {
            LoanApplications loanApplications =  getLoanApplicationId(id);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(loanApplications);
            session.getTransaction().commit();
            session.close();
            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }

    /**
     * This method is used to UPDATE Loan Application details in database.
     *
     * @param loanApplications This contains the Loan Application object with details to be
     *                 updated with
     *
     * @return Boolean This returns a true/false based on whether the Loan Application
     *                 was successfully UPDATED or not.
     */
    @Override
    public boolean updateLoanApplication(LoanApplications loanApplications) {
        try(Session session=getSession()){
            session.beginTransaction();
            try {
                session.update(loanApplications);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }

        }
    }

}
