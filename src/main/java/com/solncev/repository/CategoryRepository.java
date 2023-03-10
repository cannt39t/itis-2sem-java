package com.solncev.repository;


import com.solncev.model.Category;
import com.solncev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category getById(Integer id);
    Category findByProductsContains(Product product);

}

