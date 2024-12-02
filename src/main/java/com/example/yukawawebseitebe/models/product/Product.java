package com.example.yukawawebseitebe.models.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "category")
    private ProductCategory category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_1")
    private byte[] image1;

    @Column(name = "image_2")
    private byte[] image2;

    @Column(name = "image_3")
    private byte[] image3;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;
}
