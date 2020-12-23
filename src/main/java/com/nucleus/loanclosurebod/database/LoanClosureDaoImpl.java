package com.nucleus.loanclosurebod.database;

import com.nucleus.customer.model.Customer;
import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * This method retrieves the list of all entries of Repayment Schedule
     * for a particular Loan Application from the database.
     * @param loanApplicationNumber
     * @return list of all entries of Repayment Schedule
     */
    @Override
    public List<RepaymentSchedule> getRepaymentSchedule(int loanApplicationNumber){
        List<RepaymentSchedule> list = new ArrayList<>();
        /* Retrieving all the entries of Repayment Schedule for given Loan Application
           in the list. */
        try{
            Session session = getSession();
            session.beginTransaction();
            Query<RepaymentSchedule> query = session.createQuery("from RepaymentSchedule r where r.loanApplicationNumber=?1", RepaymentSchedule.class);
            query.setParameter(1, loanApplicationNumber);
            list = query.getResultList();
            session.getTransaction().commit();
            session.close();
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
        try{
            Session session = getSession();
            session.beginTransaction();
            loanApplication.setStatus(newStatus);
            session.update(loanApplication);
            session.getTransaction().commit();
            updateStatus = true;
            session.close();
        }catch (Exception exception) {
            updateStatus = false;
            exception.printStackTrace();
        }
        return updateStatus;
    }


    @Override
    public LoanApplications getLoanDetails(int loanApplicationNumber){
        Session session = sessionFactory.openSession();
        LoanApplications loanApplication = session.get(LoanApplications.class, loanApplicationNumber);
        session.close();
        return loanApplication;
    }

    @Override
    public List<LoanApplications> getCustomerLoanDetails(String customerCode){
        Session session = sessionFactory.openSession();
        List<LoanApplications> loanApplications=null;
        Customer customer = session.get(Customer.class, customerCode);
        if(customer != null) {
            loanApplications=customer.getLoanApplications();
        }
        session.close();
        return loanApplications;
    }

//    @Override
//    public void addDummyData() {
//        Session session = getSession();
//        session.beginTransaction();
//
//        LoanApplications loanApplication = new LoanApplications();
//        loanApplication.setLoanApplicationNumber(1);
//        loanApplication.setAgreementDate(LocalDate.of(2020, 06, 10));
//        loanApplication.setAuthorizedBy("Apurv");
//        loanApplication.setAuthorizedDate(LocalDate.of(2020, 06, 10));
//        loanApplication.setCreateDate(LocalDate.of(2020, 06, 10));
//        loanApplication.setCreatedBy("Richa");
//        loanApplication.setInstallmentDueDate(LocalDate.of(2021, 06, 10));
//        loanApplication.setLoanAmountRequested(100000);
//        loanApplication.setStatus("Active");
//        loanApplication.setModifiedDate(LocalDate.of(2020, 9, 10));
//        loanApplication.setModifiedBy("Kirtika");
//        loanApplication.setRate(20.0);
//        loanApplication.setTenure(5);
//
//        session.save(loanApplication);
//
//        RepaymentSchedule repaymentSchedule = new RepaymentSchedule();
//        repaymentSchedule.setInstallmentNumber(1);
//        repaymentSchedule.setLoanApplicationn(loanApplication);
//        repaymentSchedule.setBillFlag("Y");
//        repaymentSchedule.setClosingBalance(0);
//        repaymentSchedule.setDueDate(LocalDate.of(2021, 06, 10));
//        repaymentSchedule.setEMI(20000);
//        repaymentSchedule.setInterestComponent(0.2);
//        repaymentSchedule.setOpeningBalance(30000);
//
//        repaymentSchedule.setPrincipalComponent(10000);
//        session.save(repaymentSchedule);
//
//        session.getTransaction().commit();
//    }

}