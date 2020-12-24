package com.nucleus.eligibiltyparameter.database;

import com.nucleus.eligibiltyparameter.model.EligibilityParameter;
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

@Repository
@PropertySource("classpath:status.properties")
public class EligibilityParameterDAOImpl implements EligibilityParameterDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    //Getting status field values from status.properties file:
    @Value("${status.pending}")
    private String pending;

    @Value("${status.rejected}")
    private String rejected;

    @Value(("${status.approved}"))
    private String approved;

    @Value(("${status.saved}"))
    private String saved;

    /**
     * Getting list of all eligibility parameters
     * @return - returns a list of all eligibility parameters
     */
    public List<EligibilityParameter> getAll() {

        List<EligibilityParameter> eligibilityParameterList;
        try (Session session = getSession()){
            session.beginTransaction();
            Query<EligibilityParameter> query = session.createQuery("from EligibilityParameter e", EligibilityParameter.class);
            eligibilityParameterList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception exception) {
            eligibilityParameterList = null;
            System.out.println(exception.getMessage());
        }
        return eligibilityParameterList;
    }

    /**
     * Inserting eligibility parameters into database
     * @param eligibilityParameter - object of Eligibility Parameter to be inserted
     * @return parameterCode against which parameter is inserted
     */
    @Override
    public boolean insertParameter(EligibilityParameter eligibilityParameter) {
        boolean success;
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(eligibilityParameter);
            session.getTransaction().commit();
            success = true;
        } catch (Exception exception) {
            success = false;
            System.out.println(exception.getMessage());
        }
        return success;
    }

    /**
     * Getting a particular eligibility parameter
     * @param parameterCode - get eligibility parameter having a particular parameter code
     * @return object of eligibility parameter
     */
    @Override
    public EligibilityParameter getOneEligibilityParameter(String parameterCode) {
        EligibilityParameter eligibilityParameter;
        try (Session session = getSession()) {
            session.beginTransaction();
            Query<EligibilityParameter> query;
            query = session.createQuery("from EligibilityParameter e where e.parameterCode=?1", EligibilityParameter.class);
            query.setParameter(1, parameterCode);
            eligibilityParameter = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception exception) {
            eligibilityParameter = null;
            System.out.println(exception.getMessage());
        }
        return eligibilityParameter;
    }

    /**
     * Delete a particular eligibility parameter
     * @param parameterCode - Delete eligibility parameter against parameter code
     * @return parameter code against which eligibility parameter is deleted
     */
    @Override
    public boolean deleteEligibilityParameter(String parameterCode) {
        boolean success;
        EligibilityParameter eligibilityParameter = getOneEligibilityParameter(parameterCode);
        try (Session session = getSession()) {
            session.beginTransaction();
            session.delete(eligibilityParameter);
            session.getTransaction().commit();
            success = true;
        } catch (Exception exception) {
            success = false;
            System.out.println(exception.getMessage());
        }
        return success;
    }

    /**
     * Edit eligibility parameter
     * @param eligibilityParameter - object of eligibility parameter to be edited
     * @return - true if successfully edited else false
     */
    @Override
    public boolean editParameter(EligibilityParameter eligibilityParameter) {
        boolean success;
        if (eligibilityParameter != null && eligibilityParameter.getParameterCode()!=null) {
            try (Session session = getSession()) {
                session.beginTransaction();

                String name = eligibilityParameter.getParameterName();
                String desc = eligibilityParameter.getParameterDescription();
                double min = eligibilityParameter.getMinValue();
                double max = eligibilityParameter.getMaxValue();
                String modified = eligibilityParameter.getModifiedBy();
                String status = eligibilityParameter.getStatus();
                String code = eligibilityParameter.getParameterCode();

                Query query1 = session.createQuery("update EligibilityParameter e set e.parameterName = ?1 , " +
                        "e.parameterDescription = ?2 , e.minValue = ?3 , e.maxValue = ?4 , e.modifiedBy = ?5 ," +
                        "e.modifiedDate = ?6 , e.status = ?7 where e.parameterCode = ?8");
                query1.setParameter(1, name);
                query1.setParameter(2, desc);
                query1.setParameter(3, min);
                query1.setParameter(4, max);
                query1.setParameter(5, modified);
                query1.setParameter(6, LocalDate.now());
                query1.setParameter(7, status);
                query1.setParameter(8, code);

                query1.executeUpdate();
                session.getTransaction().commit();
                success = true;
            } catch (Exception exception) {
                success = false;
                System.out.println(exception.getMessage());
            }
        } else {
            success = false;
        }
        return success;
    }

    /**
     * Update status of eligibility parameter
     * @param parameterCode - updating status of particular eligibility parameter identified by parameter code
     * @param newStatus - new status to be updated
     * @param authorizedBy - name of the person who has updated eligibility parameter
     * @return true if updated successfully, else false
     */
    public boolean updateStatus(String parameterCode, String newStatus,String authorizedBy) {
        boolean updateStatus;
        EligibilityParameter eligibilityParameter = getOneEligibilityParameter(parameterCode);
        if (eligibilityParameter != null) {
            try (Session session = getSession()) {
                session.beginTransaction();
                eligibilityParameter.setAuthorizedBy(authorizedBy);
                eligibilityParameter.setAuthorizedDate(LocalDate.now());
                eligibilityParameter.setStatus(newStatus);
                session.update(eligibilityParameter);
                session.getTransaction().commit();
                updateStatus = true;
            } catch (Exception exception) {
                updateStatus = false;
                System.out.println(exception.getMessage());
            }
            return updateStatus;
        } else {
            return false;
        }
    }

    /**
     * Fetching list of all eligibility parameters whose status is approved
     * @return - list of all eligibility parameters whose status is approved
     */
    @Override
    public List<EligibilityParameter> getApprovedParameters(){
        List<EligibilityParameter> eligibilityParameterList;
        try (Session session = getSession()){
            session.beginTransaction();
            Query<EligibilityParameter> query = session.createQuery("from EligibilityParameter e where e.status= ?1", EligibilityParameter.class);
            query.setParameter(1, approved);
            eligibilityParameterList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception exception) {
            eligibilityParameterList = null;
            System.out.println(exception.getMessage());
        }
        return eligibilityParameterList;

    }
}