package com.nucleus.loanaplications.dao;

import com.nucleus.customerservice.loandisbursal.model.LoanApplication;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public boolean addApplication(LoanApplication loanApplication) {
        return false;
    }

    @Override
    public List<LoanApplication> getLoanApplicationList() {
        return null;
    }
}
