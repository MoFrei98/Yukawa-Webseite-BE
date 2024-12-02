package com.example.yukawawebseitebe.models.order;

import com.example.yukawawebseitebe.models.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name = "order_uuid", referencedColumnName = "uuid")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_uuid", referencedColumnName = "uuid")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
