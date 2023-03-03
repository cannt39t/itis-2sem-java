package org.example.repository.repository1;


import org.example.model.model1.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getAllByClientId(Integer id);
    Order getOrderById(Integer id);

}
