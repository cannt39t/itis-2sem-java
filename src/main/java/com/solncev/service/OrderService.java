package com.solncev.service;


import com.solncev.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllByClientId(Integer id);
    Order getOrderById(Integer id);

}
