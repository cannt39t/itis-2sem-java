package org.example.services;

import org.example.model.model1.Product;

import java.util.List;

public interface ProductService {

    Product getByName(String name);
    List<Product> getByNameStartsWith(String name);

}
