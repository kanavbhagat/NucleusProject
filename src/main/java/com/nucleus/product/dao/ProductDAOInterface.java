package com.nucleus.product.dao;

import com.nucleus.product.model.Product;

import java.util.List;

public interface ProductDAOInterface {

    List<Product> getProductList();

    Boolean createNewProduct(Product product);
}
