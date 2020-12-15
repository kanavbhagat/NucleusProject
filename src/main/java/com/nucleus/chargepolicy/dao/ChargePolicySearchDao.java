package com.nucleus.chargepolicy.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import com.nucleus.chargepolicy.model.ChargePolicy;

import org.hibernate.query.Query;
import java.util.List;

@Repository
public class ChargePolicySearchDao {
    Configuration configuration;
    public ChargePolicySearchDao(){
        configuration = new Configuration();
        configuration.addAnnotatedClass(ChargePolicy.class);
        configuration.configure();
        System.out.println(" Configuration Done !");
    }

    public List<ChargePolicy> getChargePolicyList(){
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        session.getTransaction().begin();
        Query query =  session.createQuery("from ChargePolicy");
        List<ChargePolicy> chargePolicyList = query.getResultList();
        System.out.println("in dao ");

        return chargePolicyList;
    }

}
