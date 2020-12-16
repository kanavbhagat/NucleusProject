package com.nucleus.chargepolicy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nucleus.chargepolicy.model.ChargePolicy;

@Repository
public class ChargePolicyDao {

    Configuration configuration;
    SessionFactory sessionFactory;

    public ChargePolicyDao(){
        configuration = new Configuration();
        configuration.addAnnotatedClass(ChargePolicy.class);
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        System.out.println(" Configuration Done !");
    }
    public void insert(ChargePolicy chargePolicy){
        System.out.println("In dao with " + chargePolicy.getChargePolicyName());
        //SessionFactory factory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(chargePolicy);
        session.getTransaction().commit();
        sessionFactory.close();
    }


}
