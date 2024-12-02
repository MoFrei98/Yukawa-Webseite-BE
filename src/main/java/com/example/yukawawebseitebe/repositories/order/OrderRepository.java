package com.example.yukawawebseitebe.repositories.order;

import com.example.yukawawebseitebe.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
