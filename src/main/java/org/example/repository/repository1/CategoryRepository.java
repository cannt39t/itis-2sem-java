package org.example.repository.repository1;

import org.example.model.model1.Category;
import org.example.model.model1.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category getByIdNotNull(Integer id);

    Category findByProductsContains(Product product);

}

