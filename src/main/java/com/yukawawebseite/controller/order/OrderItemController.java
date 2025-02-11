package com.yukawawebseite.controller.order;

import com.yukawawebseite.models.order.OrderItem;
import com.yukawawebseite.services.order.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/get-all")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable String uuid) {
        Optional<OrderItem> orderItem = orderItemService.getOrderItemById(uuid);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    @PutMapping("/update")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(orderItem);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable String uuid) {
        orderItemService.deleteOrderItem(uuid);
        return ResponseEntity.noContent().build();
    }
}
