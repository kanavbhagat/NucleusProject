package com.nucleus.chargepolicy.dao;

import com.nucleus.chargepolicy.model.ChargePolicy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
/*public class ChargePolicyDao {
    @Autowired
    SessionFactory sessionFactory;

    public void insert(ChargePolicy chargePolicy) {
        System.out.println("In dao with " + chargePolicy.getChargePolicyName());
        //SessionFactory factory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(chargePolicy);
        session.getTransaction().commit();
        sessionFactory.close();
    }
}*/

    public interface ChargePolicyDao {
        public void insert(ChargePolicy chargePolicy);

        public List<ChargePolicy> getPolicyList();

        public ChargePolicy getChargePolicy(String chargePolicyCode);
    }
