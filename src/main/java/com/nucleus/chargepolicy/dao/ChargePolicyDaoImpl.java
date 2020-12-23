package com.nucleus.chargepolicy.dao;

import com.nucleus.chargepolicy.model.ChargePolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ChargePolicyDaoImpl implements ChargePolicyDao{
    @Autowired
    private SessionFactory sessionFactory;
    // private SessionFactory sessionFactory = (SessionFactory) new AppConfig().getSessionFactoryBean();


    private Session getSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException E) {
            session = sessionFactory.openSession();
        }
        return session;
    }
    public int insert(ChargePolicy chargePolicy) {
        System.out.println("In Service insert query");
        int insertStatus = 0;
        String todayDate = LocalDate.now().toString();
        chargePolicy.setCreatedDate(todayDate);
        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(chargePolicy);
            session.getTransaction().commit();
            insertStatus = 1;
        } catch (Exception exception) {
            System.out.println("*************************************");
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            if(exception.getMessage().contains("ConstraintViolation"))insertStatus = 2;
            System.out.println("*************************************");

        }

        //SessionFactory factory = configuration.buildSessionFactory();
        return insertStatus;
    }

    public List<ChargePolicy> getPolicyList(){
        List<ChargePolicy> chargePolicyList;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query query = session.createQuery("from ChargePolicy");
            chargePolicyList = query.getResultList();
            System.out.println(query.getResultList());
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            chargePolicyList = null;
        }
        return chargePolicyList;
    }
    public ChargePolicy getChargePolicy(String chargePolicyCode){
        ChargePolicy chargePolicy;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query query = session.createQuery("from ChargePolicy cp where cp.chargePolicyCode = '"+chargePolicyCode+"'");;
            chargePolicy= (ChargePolicy)query.getSingleResult();
            System.out.println(chargePolicy.getChargePolicyName() + "---------------------------------------------------------------" );
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            chargePolicy = null;
            exception.printStackTrace();
        }
        return chargePolicy;

    }
    public void updateStatus(String chargePolicyCode,String status,String approvedBy){
        ChargePolicy chargePolicy;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query q=session.createQuery("update ChargePolicy set status=:status,authorized_by=:user where policy_code=:policyCode");
            q.setParameter("status",status);
            q.setParameter("user",approvedBy);
            q.setParameter("policyCode",chargePolicyCode);

            int s=q.executeUpdate();
            System.out.println("Status  updated as "+ s);
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            chargePolicy = null;
            exception.printStackTrace();
        }
    }

    public void updateEntry(ChargePolicy chargePolicy){
        try{
            Session session = getSession();
            session.beginTransaction();
            session.update(chargePolicy);
            session.getTransaction().commit();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public int deleteChargePolicy(String chargePolicyCode){
        int s = 0;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query q=session.createQuery("delete from ChargePolicy where chargePolicyCode= :chargePolicyCode");
            q.setParameter("chargePolicyCode", chargePolicyCode);
            s=q.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return s;
    }
}
