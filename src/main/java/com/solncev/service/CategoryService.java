package com.solncev.service;


import com.solncev.model.Category;
import com.solncev.model.Product;

public interface CategoryService {

    Category getByIdNotNull(Integer id);
    Category findByProductsContains(Product product);

}


// ХАЧУ ЕСТЬ