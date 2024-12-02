package com.example.yukawawebseitebe.models.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "uuid")
    public String uuid = UUID.randomUUID().toString();

    @Column(name = "role")
    private UserRole role = UserRole.GUEST;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_last_login")
    private LocalDateTime dateLastLogin;
}
