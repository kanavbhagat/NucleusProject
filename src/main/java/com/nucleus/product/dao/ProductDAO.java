package com.nucleus.product.dao;

import com.nucleus.product.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO implements ProductDAOInterface {

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

    @Override
    public List<Product> getProductList() {
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery("from Product p");
            List<Product> productList = query.list();
            session.getTransaction().commit();
            return productList;
        }
    }

    @Override
    public Boolean createNewProduct(Product product) {
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.save(product);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e){
                e.printStackTrace();
                session.getTransaction().rollback();
                return false;
            }
        }
    }
}
