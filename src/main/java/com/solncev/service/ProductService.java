package com.solncev.service;


import com.solncev.model.Product;

import java.util.List;

public interface ProductService {

    Product getByName(String name);
    List<Product> getByNameStartsWith(String name);

}
