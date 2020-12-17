package com.nucleus.customerservice.customerloansearch.dao;

import com.nucleus.loanaplications.model.LoanApplications;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoanApplicationsDAO implements LoanApplicationsDAOInterface{

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
    public LoanApplications getLoanApplicationDetails(Integer loanApplicationNumber) {
        try(Session session=getSession()){
            LoanApplications loanApplications=new LoanApplications();
            session.beginTransaction();
            try {
                loanApplications=session.get(LoanApplications.class,loanApplicationNumber);
                session.getTransaction().commit();
                return loanApplications;
            }catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            return null;
        }
    }
}
