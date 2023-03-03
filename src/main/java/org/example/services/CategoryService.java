package org.example.services;

import org.example.model.model1.Category;
import org.example.model.model1.Product;

public interface CategoryService {

    Category getByIdNotNull(Integer id);
    Category findByProductsContains(Product product);

}
