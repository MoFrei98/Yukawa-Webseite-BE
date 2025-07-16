package com.yukawawebseite.services.order;

import com.yukawawebseite.models.order.Order;
import com.yukawawebseite.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String uuid) {
        return orderRepository.findById(uuid);
    }

    public Order saveOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public Order updateOrder(Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(updatedOrder.getUuid());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setUser(updatedOrder.getUser());
            order.setNr(updatedOrder.getNr());
            order.setDate(updatedOrder.getDate());
            order.setStatus(updatedOrder.getStatus());
            return orderRepository.saveAndFlush(order);
        } else
            return null;
    }

    public void deleteOrder(String uuid) {
        orderRepository.deleteById(uuid);
    }

}
