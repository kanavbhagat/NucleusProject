package com.nucleus.product.service;

import com.nucleus.product.dao.ProductDAO;
import com.nucleus.product.dao.ProductDAOInterface;
import com.nucleus.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAOInterface productDAO;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }


    /**
     * <p> calls the dao which retrieves and returns a list of all the products stored in the database. </p>
     * @return a List object containing all the products. Returns an empty list if not.
     */
    public List<Product> getProductList() {
        return productDAO.getProductList();
    }


    /**
     * <p> calls the dao which stores a new product in the database. Returns true if creation was successful, else false.
     * </p>
     * @param product to be saved in the database.
     * @return Boolean true if creation was successful, else false.
     */
    public Boolean createNewProduct(Product product){
        return productDAO.createNewProduct(product);
    }


    /**
     * <p> calls the dao which retrieves a product by its id from the database. returns the product, if found, or null.
     * </p>
     * @param id productId of the product to be retrieved.
     * @return product object if it was retrieved, else null
     */
    public Product getProductById(String id){
        return productDAO.getProductById(id);
    }


    /**
     * <p> calls the dao which updates the product in the database. Returns the product if the opreation was a success,
     * else null. </p>
     * @param product : the product with the updated fields.
     * @return the updated product if operation was a success, else null.
     */
    public Product updateProduct(Product product){
        return productDAO.updateProduct(product);
    }


    /**
     * <p> calls the dao which deletes a product by its product id in the database. returns a boolean depending
     * on operation success. </p>
     * @param productId : productId of the product to be deleted.
     * @return true if the operation was a success, else false.
     */
    public Boolean deleteProduct(String productId){
        return productDAO.deleteProduct(productId);
    }
}
