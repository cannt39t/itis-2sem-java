package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.model.model1.Order;
import org.example.repository.repository1.CategoryRepository;
import org.example.repository.repository1.OrderRepository;
import org.example.services.OrderService;
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
