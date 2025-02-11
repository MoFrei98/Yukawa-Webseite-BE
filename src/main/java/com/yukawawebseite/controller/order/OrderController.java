package com.yukawawebseite.controller.order;

import com.yukawawebseite.models.order.Order;
import com.yukawawebseite.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<Order> getOrderById(@PathVariable String uuid) {
        Optional<Order> order = orderService.getOrderById(uuid);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @PutMapping("/update")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String uuid) {
        orderService.deleteOrder(uuid);
        return ResponseEntity.noContent().build();
    }
}
