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

        System.out.println("In dao with " + chargePolicy.getChargePolicyName() + " " + insertStatus);
        //SessionFactory factory = configuration.buildSessionFactory();

    }

    public List<ChargePolicy> getPolicyList(){
        System.out.println("in dao");
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
        System.out.println("In dao getCharge with code " + chargePolicyCode);
        ChargePolicy chargePolicy;
        try {
            Session session = getSession();
            session.beginTransaction();
            Query query = session.createQuery("from ChargePolicy cp where cp.chargePolicyCode = '"+chargePolicyCode+"'");
            chargePolicy= (ChargePolicy)query.getSingleResult();
            System.out.println("Charge Policy "  + chargePolicy);
            session.getTransaction().commit();
            session.close();
        } catch(Exception exception) {
            chargePolicy = null;
            exception.printStackTrace();
        }
        return chargePolicy;

    }
}
