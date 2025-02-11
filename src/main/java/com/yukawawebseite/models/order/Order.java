package com.yukawawebseite.models.order;

import com.yukawawebseite.models.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private User user;

    @Column(name = "nr")
    private int nr;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private OrderStatus status;
}
