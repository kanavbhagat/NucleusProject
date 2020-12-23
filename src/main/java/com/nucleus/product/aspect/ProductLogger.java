package com.nucleus.product.aspect;


import com.nucleus.product.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    @AfterReturning("create()")
    public void createNewProduct(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Product product = (Product) args[0];
        logger.info("New product created with ProductCode "+product.getProductCode());
    }

    @AfterReturning("getProduct()")
    public void getProductList(){
        logger.info("Product list fetched from database");
    }

    @AfterReturning("update()")
    public void updateProduct(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Product product = (Product) args[0];
        logger.info("Product updated with status "+product.getStatus());
    }

    @AfterReturning("productById()")
    public void getProductById(){
        logger.info("Product fetched by Id from database");
    }

    @AfterReturning("delete()")
    public void deleteProduct(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String id = (String) args[0];
        logger.info("Product deleted with Product Id "+id);
    }
}
