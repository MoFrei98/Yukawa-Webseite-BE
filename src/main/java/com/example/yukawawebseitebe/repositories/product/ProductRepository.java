package com.example.yukawawebseitebe.repositories.product;

import com.example.yukawawebseitebe.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
