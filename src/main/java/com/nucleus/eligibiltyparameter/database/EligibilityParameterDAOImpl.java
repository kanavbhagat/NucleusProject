package com.nucleus.eligibiltyparameter.database;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EligibilityParameterDAOImpl implements EligibilityParameterDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<EligibilityParameter> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<EligibilityParameter> query = session.createQuery("FROM  EligibilityParameter");
        List<EligibilityParameter> list = query.getResultList();
        session.getTransaction().commit();

        return list;
    }

    @Override
    public void insertParameter(EligibilityParameter eligibilityParameter){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
        }

    }

    @Override
    public void insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
        }

    }
}
