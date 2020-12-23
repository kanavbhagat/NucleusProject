package com.nucleus.product.dao;

import com.nucleus.product.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * <p> Repository for product data access. </p>
 */
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


    /**
     * <p> retrieves and returns a list of all the products stored in the database. </p>
     * @return a List object containing all the products. Returns an empty list if not.
     */
    @Override
    public List<Product> getProductList() {
        try(Session session = getSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery("from Product p", Product.class);
            List<Product> productList = query.list();
            session.getTransaction().commit();
            return productList;
        }
    }


    /**
     * <p> stores a new product in the database. Returns true if creation was successful, else false. </p>
     * @param Product product to be saved in the database.
     * @return Boolean true if creation was successful, else false.
     */
    @Override
    public Boolean createNewProduct(Product product) {
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.save(product);
                session.getTransaction().commit();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return false;
            }
        }
    }


    /**
     * <p> retrieves a product by its id from the database. returns the product, if found, or null. </p>
     * @param String id productId of the product to be retrieved.
     * @return product object if it was retrieved, else null
     */
    public Product getProductById(String id){
        try(Session session = getSession()){
            Product product;
            session.beginTransaction();
            try {
                product = session.get(Product.class, id);
                session.getTransaction().commit();
                return product;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return null;
            }
        }
    }


    /**
     * <p> updates the product in the database. Returns the product if the opreation was a success, else null. </p>
     * @param Product product : the product with the updated fields.
     * @return the updated product if operation was a success, else null.
     */
    public Product updateProduct(Product product){
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                session.update(product);
                session.getTransaction().commit();
                return product;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return null;
            }
        }
    }


    /**
     * <p> deletes a product by its product id in the database. returns a boolean depending on operation success. </p>
     * @param String productId : productId of the product to be deleted.
     * @return true if the operation was a success, else false.
     */
    public Boolean deleteProduct(String productId){
        try(Session session = getSession()){
            session.beginTransaction();
            try {
                Product product = session.get(Product.class, productId);
                session.delete(product);
                session.getTransaction().commit();
                return true;
            } catch (Exception e){
                System.out.println(e.getMessage());
                session.getTransaction().rollback();
                return false;
            }
        }
    }
}
