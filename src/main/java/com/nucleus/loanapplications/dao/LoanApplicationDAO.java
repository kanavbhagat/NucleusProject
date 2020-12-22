package com.nucleus.loanapplications.dao;

import com.nucleus.loanapplications.model.LoanApplications;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public boolean addApplication(LoanApplications loanApplications) {

        boolean successful = false;
        try
        {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(loanApplications);
            session.getTransaction().commit();
            session.close();
            successful=true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return successful;
    }

    public List<LoanApplications> getLoanApplicationList(){
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<LoanApplications> query = session.createQuery("from LoanApplications");
            List<LoanApplications> loanApplicationsList = query.list();
            session.getTransaction().commit();
            return loanApplicationsList;
        }

    }

}
