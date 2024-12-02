package com.example.yukawawebseitebe.services.product;

import com.example.yukawawebseitebe.models.product.Product;
import com.example.yukawawebseitebe.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String uuid) {
        return productRepository.findById(uuid);
    }

    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public Product updateProduct(Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(updatedProduct.getUuid());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setCategory(updatedProduct.getCategory());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setImage1(updatedProduct.getImage1());
            product.setImage2(updatedProduct.getImage2());
            product.setImage3(updatedProduct.getImage3());
            product.setStock(updatedProduct.getStock());
            product.setPrice(updatedProduct.getPrice());
            product.setDiscount(updatedProduct.getDiscount());
            return productRepository.saveAndFlush(product);
        } else
            return null;
    }

    public void deleteProduct(String uuid) {
        productRepository.deleteById(uuid);
    }
}