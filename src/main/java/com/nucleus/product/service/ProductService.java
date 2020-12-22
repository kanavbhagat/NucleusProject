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

    public List<Product> getProductList() {
        return productDAO.getProductList();
    }

    public Boolean createNewProduct(Product product){
        return productDAO.createNewProduct(product);
    }

    public Product getProductById(String id){
        return productDAO.getProductById(id);
    }

}
