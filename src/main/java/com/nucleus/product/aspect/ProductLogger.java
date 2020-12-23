package com.nucleus.product.aspect;


import com.nucleus.product.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class ProductLogger {

    Logger logger = LoggerFactory.getLogger(ProductLogger.class);

    @Pointcut("execution (* com.nucleus.product.service.ProductService.createNewProduct(..))")
    public void create(){}

    @Pointcut("execution (* com.nucleus.product.service.ProductService.getProductList(..))")
    public void getProduct(){}

    @Pointcut("execution (* com.nucleus.product.service.ProductService.getProductById(..))")
    public void productById(){}

    @Pointcut("execution (* com.nucleus.product.service.ProductService.updateProduct(..))")
    public void update(){}

    @Pointcut("execution (* com.nucleus.product.service.ProductService.deleteProduct(..))")
    public void delete(){}

    @AfterReturning(pointcut = "create()",returning = "isCreated")
    public void createNewProduct(JoinPoint joinPoint,boolean isCreated){
        Object[] args = joinPoint.getArgs();
        if(isCreated){
            Product product = (Product) args[0];
            logger.info("New product created with ProductCode " + product.getProductCode());
        } else {
            logger.info("New product not created. Method returned " + isCreated);
        }
    }

    @AfterReturning(pointcut = "getProduct()",returning = "productList")
    public void getProductList(JoinPoint joinPoint, List<Product> productList){
        if( productList!=null && !productList.isEmpty()) {
            logger.info("Product list fetched from database with size "+productList.size());
        } else {
            logger.info("Product list fetched, but returned empty or null.");
        }
    }

    @AfterReturning(pointcut = "update()",returning = "project")
    public void updateProduct(JoinPoint joinPoint,Object project){
        Object[] args = joinPoint.getArgs();
        if(project!=null) {
            Product product = (Product) args[0];
            logger.info("Product updated with status " + product.getStatus());
        } else{
            logger.info("Product not updated. ProductDAO.update() returned null.");
        }
    }

    @AfterReturning(pointcut = "productById()",returning = "project")
    public void getProductById(JoinPoint joinPoint,Object project){
        Object[] args = joinPoint.getArgs();
        if(project!=null) {
            String id = (String) args[0];
            logger.info("Product fetched by Id from database with id " + id);
        }else{
            logger.info("Product not fetched by id. ProductDAO.getProductById() returned null.");
        }
    }

    @AfterReturning(pointcut = "delete()",returning = "isDeleted")
    public void deleteProduct(JoinPoint joinPoint,boolean isDeleted){
        Object[] args = joinPoint.getArgs();
        if(isDeleted) {
            String id = (String) args[0];
            logger.info("Product deleted with Product Id " + id);
        } else {
            logger.info("Product not deleted. ProductDAO.delete() returned " + isDeleted);
        }
    }
}
