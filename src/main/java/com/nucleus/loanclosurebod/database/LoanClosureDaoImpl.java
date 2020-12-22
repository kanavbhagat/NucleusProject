package com.nucleus.loanclosurebod.database;

import com.nucleus.loanapplications.model.LoanApplications;
import com.nucleus.loanclosurebod.model.RepaymentSchedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class LoanClosureDaoImpl implements LoanClosureDao{

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
    public List<LoanApplications> getLoanApplications(){
        List<LoanApplications> list = new ArrayList<>();
        try{
            Session session = getSession();
            session.beginTransaction();
            Query<LoanApplications> query = session.createQuery("FROM  LoanApplication", LoanApplications.class);
            list = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public List<RepaymentSchedule> getRepaymentSchedule(LoanApplications loanApplication){
        List<RepaymentSchedule> list = new ArrayList<>();
        try{
            Session session = getSession();
            session.beginTransaction();
            Query<RepaymentSchedule> query = session.createQuery("from RepaymentSchedule r where r.loanApplication=?1", RepaymentSchedule.class);
            query.setParameter(1, loanApplication);
            list = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateStatus(LoanApplications loanApplication, String newStatus){
        boolean updateStatus;
        try{
            Session session = getSession();
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

    @Override
    public void addDummyData() {
        Session session = getSession();
        session.beginTransaction();

        LoanApplications loanApplication = new LoanApplications();
        loanApplication.setLoanApplicationNumber(1);
        loanApplication.setAgreementDate(LocalDate.of(2020, 06, 10));
        loanApplication.setAuthorizedBy("Apurv");
        loanApplication.setAuthorizedDate(LocalDate.of(2020, 06, 10));
        loanApplication.setCreateDate(LocalDate.of(2020, 06, 10));
        loanApplication.setCreatedBy("Richa");
        loanApplication.setInstallmentDueDate(LocalDate.of(2021, 06, 10));
        loanApplication.setLoanAmountRequested(100000);
        loanApplication.setStatus("Active");
        loanApplication.setModifiedDate(LocalDate.of(2020, 9, 10));
        loanApplication.setModifiedBy("Kirtika");
        loanApplication.setRate(20.0);
        loanApplication.setTenure(5);

        session.save(loanApplication);

        RepaymentSchedule repaymentSchedule = new RepaymentSchedule();
        repaymentSchedule.setInstallmentNumber(1);
        repaymentSchedule.setLoanApplicationn(loanApplication);
        repaymentSchedule.setBillFlag("Y");
        repaymentSchedule.setClosingBalance(0);
        repaymentSchedule.setDueDate(LocalDate.of(2021, 06, 10));
        repaymentSchedule.setEMI(20000);
        repaymentSchedule.setInterestComponent(0.2);
        repaymentSchedule.setOpeningBalance(30000);

        repaymentSchedule.setPrincipalComponent(10000);
        session.save(repaymentSchedule);

        session.getTransaction().commit();
    }
}