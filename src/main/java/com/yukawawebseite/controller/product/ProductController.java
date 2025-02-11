package com.yukawawebseite.controller.product;

import com.yukawawebseite.models.product.Product;
import com.yukawawebseite.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<Product> getProductById(@PathVariable String uuid) {
        Optional<Product> product = productService.getProductById(uuid);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity.noContent().build();
    }
}
