package com.yukawawebseite.services.order;

import com.yukawawebseite.models.order.OrderItem;
import com.yukawawebseite.repositories.order.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(String uuid) {
        return orderItemRepository.findById(uuid);
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.saveAndFlush(orderItem);
    }

    public OrderItem updateOrderItem(OrderItem updatedOrderItem) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(updatedOrderItem.getUuid());
        if (optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            orderItem.setOrder(updatedOrderItem.getOrder());
            orderItem.setProduct(updatedOrderItem.getProduct());
            orderItem.setQuantity(updatedOrderItem.getQuantity());
            return orderItemRepository.saveAndFlush(orderItem);
        } else
            return null;
    }

    public void deleteOrderItem(String uuid) {
        orderItemRepository.deleteById(uuid);
    }
}
