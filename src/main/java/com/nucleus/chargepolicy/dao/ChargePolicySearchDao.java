package com.nucleus.chargepolicy.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.nucleus.chargepolicy.model.ChargePolicy;

import org.hibernate.query.Query;
import java.util.List;

@Repository
public class ChargePolicySearchDao {
    @Autowired
    SessionFactory sessionFactory;

    public List<ChargePolicy> getChargePolicyList(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query<ChargePolicy> query =  session.createQuery("from ChargePolicy");
        List<ChargePolicy> chargePolicyList = query.getResultList();
        System.out.println("in dao ");

        return chargePolicyList;
    }

}
