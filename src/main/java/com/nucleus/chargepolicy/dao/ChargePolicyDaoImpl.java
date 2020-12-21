package com.nucleus.chargepolicy.dao;

import com.nucleus.chargepolicy.model.ChargePolicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void insert(ChargePolicy chargePolicy) {
        System.out.println("In Service insert query");
        boolean insertStatus;
        String todayDate = LocalDate.now().toString();
        chargePolicy.setCreatedDate(todayDate);
        try {
            Session session = getSession();
            session.beginTransaction();
            session.save(chargePolicy);
            session.getTransaction().commit();
            insertStatus = true;
        } catch (Exception exception) {
            exception.printStackTrace();
            insertStatus = false;
        }

        //SessionFactory factory = configuration.buildSessionFactory();

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
    public void updateStatus(String chargePolicyCode,String status){
        ChargePolicy chargePolicy;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query q=session.createQuery("update ChargePolicy set status=:status where policy_code=:policyCode");
            q.setParameter("status",status);
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
        boolean updateStatus;
        try{
            Session session = getSession();
            session.beginTransaction();
            session.update(chargePolicy);
            session.getTransaction().commit();

        } catch (Exception exception) {
            updateStatus = false;
            exception.printStackTrace();
        }
    }
    public void deleteChargePolicy(String chargePolicyCode){
        try {
            Session session = getSession();
            session.beginTransaction();
            Query q=session.createQuery("delete from ChargePolicy where chargePolicyCode= :chargePolicyCode");
            q.setParameter("chargePolicyCode", chargePolicyCode);
            int s=q.executeUpdate();
            System.out.println("Status  deleted as "+ s);
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
