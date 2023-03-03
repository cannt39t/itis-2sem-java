package org.example.services;

import org.example.model.model1.Order;

import java.util.List;

public interface ClientService {

    List<Order> getAllByClientId(Integer id);
    Order getOrderById(Integer id);

}
