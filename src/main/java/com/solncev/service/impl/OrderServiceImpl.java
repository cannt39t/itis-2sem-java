package com.solncev.service.impl;

import com.solncev.model.Order;
import com.solncev.repository.OrderRepository;
import com.solncev.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllByClientId(Integer id) {
        return orderRepository.getAllByClientId(id);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.getOrderById(id);
    }
}
