package com.nucleus.eligibiltyparameter.database;

import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class EligibilityParameterDAOImpl implements EligibilityParameterDAO {
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

<<<<<<< HEAD
=======
    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E) {
            session = sessionFactory.openSession();
        }
        return session;
    }

>>>>>>> f3dea3e8b05710ff514aebc41edfc71353014f97
    public List<EligibilityParameter> getAll() {

        List<EligibilityParameter> eligibilityParameterList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<EligibilityParameter> query = session.createQuery("from EligibilityParameter e", EligibilityParameter.class);
            eligibilityParameterList = query.getResultList();
            session.getTransaction().commit();
            session.close();
<<<<<<< HEAD
        } catch(Exception exception) {
=======
        } catch (Exception exception) {
>>>>>>> f3dea3e8b05710ff514aebc41edfc71353014f97
            eligibilityParameterList = null;
        }
        return eligibilityParameterList;
    }

    @Override
<<<<<<< HEAD
    public void insertParameter(EligibilityParameter eligibilityParameter){
=======
    public void insertParameter(EligibilityParameter eligibilityParameter) {
>>>>>>> f3dea3e8b05710ff514aebc41edfc71353014f97

        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
<<<<<<< HEAD
    public void insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter){
=======
    public void insertParameterAndRequestApproval(EligibilityParameter eligibilityParameter) {
>>>>>>> f3dea3e8b05710ff514aebc41edfc71353014f97
        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
    @Override
    public EligibilityParameter getOneEligibilityParameter(String parameterCode) {
        EligibilityParameter eligibilityParameter;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query<EligibilityParameter> query = session.createQuery("from EligibilityParameter e where e.parameterCode=?1", EligibilityParameter.class);
            query.setParameter(1, parameterCode);
            eligibilityParameter = query.getSingleResult();
<<<<<<< HEAD
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            eligibilityParameter = null;
        }
        return eligibilityParameter;
    }

    @Override
    public boolean deleteEligibilityParameter(String parameterCode){
        boolean deleteStatus;
        EligibilityParameter eligibilityParameter = getOneEligibilityParameter(parameterCode);
        try{
            Session session = getSession();
            session.beginTransaction();
            session.delete(eligibilityParameter);
            session.getTransaction().commit();
            deleteStatus = true;
            session.close();
        } catch (Exception exception) {
            deleteStatus = false;
            exception.printStackTrace();
        }
        return deleteStatus;
    }

    @Override
    public boolean editParameter(EligibilityParameter eligibilityParameter){
        try {
            Session session = getSession();
            session.beginTransaction();
            System.out.println("inside edit query");
            Query query1=session.createQuery("from EligibilityParameter e where e.parameterCode= ?1");
            query1.setParameter(1,eligibilityParameter.getParameterCode());
            EligibilityParameter ep=null;
            ep=(EligibilityParameter)query1.getSingleResult();
            if(ep==null)
                System.out.println("null true");


           query1 = session.createQuery("update EligibilityParameter e set e.parameterName = ?1 , " +
                    "e.parameterDescription = ?2 , e.minValue = ?3 , e.maxValue = ?4 , e.modifiedBy = ?5 ," +
                    "e.modifiedDate = ?6 , e.status = ?7 where e.parameterCode = ?8");

            System.out.println(eligibilityParameter.getParameterName());
            String name=eligibilityParameter.getParameterName();
            String desc= eligibilityParameter.getParameterDescription();
            double min= eligibilityParameter.getMinValue();
            double max=eligibilityParameter.getMaxValue();
            String modified= eligibilityParameter.getModifiedBy();
            String status=eligibilityParameter.getStatus();
            String code=eligibilityParameter.getParameterCode();

            query1.setParameter(1, name);
            query1.setParameter(2, desc);
            query1.setParameter(3 , min);
            query1.setParameter(4 ,max);
            query1.setParameter(5,modified);
            query1.setParameter(6, LocalDate.now());
            query1.setParameter(7,status);
            query1.setParameter(8,code);

            System.out.println(eligibilityParameter.getParameterName());
            query1.executeUpdate();


            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            return false;

        }
        return true;
=======
            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            eligibilityParameter = null;
        }
        return eligibilityParameter;
    }

    @Override
    public boolean deleteEligibilityParameter(String parameterCode) {
        boolean deleteStatus;
        EligibilityParameter eligibilityParameter = getOneEligibilityParameter(parameterCode);
        try {
            Session session = getSession();
            session.beginTransaction();
            session.delete(eligibilityParameter);
            session.getTransaction().commit();
            deleteStatus = true;
            session.close();
        } catch (Exception exception) {
            deleteStatus = false;
            exception.printStackTrace();
        }
        return deleteStatus;
    }

    @Override
    public boolean editParameter(EligibilityParameter eligibilityParameter) {
        try {
            Session session = getSession();
            session.beginTransaction();

            Query query1 = session.createQuery("update EligibilityParameter e set e.parameterName = ?1 , " +
                    "e.parameterDescription = ?2 , e.minValue = ?3 , e.maxValue = ?4 , e.modifiedBy = ?5 ," +
                    "e.modifiedDate = ?6 , e.status = ?7 where e.parameterCode = ?8");

            System.out.println(eligibilityParameter.getParameterName());
            String name = eligibilityParameter.getParameterName();
            String desc = eligibilityParameter.getParameterDescription();
            double min = eligibilityParameter.getMinValue();
            double max = eligibilityParameter.getMaxValue();
            String modified = eligibilityParameter.getModifiedBy();
            String status = eligibilityParameter.getStatus();
            String code = eligibilityParameter.getParameterCode();

            query1.setParameter(1, name);
            query1.setParameter(2, desc);
            query1.setParameter(3, min);
            query1.setParameter(4, max);
            query1.setParameter(5, modified);
            query1.setParameter(6, LocalDate.now());
            query1.setParameter(7, status);
            query1.setParameter(8, code);

            System.out.println(eligibilityParameter.getParameterName());
            query1.executeUpdate();

>>>>>>> f3dea3e8b05710ff514aebc41edfc71353014f97

            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            return false;

        }
        return true;

    }

    public boolean updateStatus(String parameterCode, String newStatus) {
        boolean updateStatus;
        try {
            Session session = getSession();
            session.beginTransaction();
            EligibilityParameter eligibilityParameter = getOneEligibilityParameter(parameterCode);
            eligibilityParameter.setStatus(newStatus);
            session.update(eligibilityParameter);
            session.getTransaction().commit();
            updateStatus = true;
        } catch (Exception exception) {
            updateStatus = false;
        }
        return updateStatus;
    }
<<<<<<< HEAD

    public boolean updateStatus(String parameterCode,String newStatus){
        boolean updateStatus;
        try{
            Session session = getSession();
            session.beginTransaction();
            EligibilityParameter eligibilityParameter = getOneEligibilityParameter(parameterCode);
            eligibilityParameter.setStatus(newStatus);
            session.update(eligibilityParameter);
            session.getTransaction().commit();
            updateStatus = true;
        } catch (Exception exception) {
            updateStatus = false;
        }
        return updateStatus;
    }
}
=======
}
>>>>>>> f3dea3e8b05710ff514aebc41edfc71353014f97
