package org.example.repository.repository1;


import org.example.model.model1.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product getByName(String name);
    List<Product> getByNameStartsWith(String name);

}
