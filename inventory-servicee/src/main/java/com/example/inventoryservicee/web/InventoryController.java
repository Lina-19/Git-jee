package com.example.inventoryservicee.web;

import com.example.inventoryservicee.entities.Product;
import com.example.inventoryservicee.reposirories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InventoryController {
    @Autowired
    private final ProductRepository productRepository;

    public InventoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id);
    }

    @PostMapping("/products/create")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/update/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product updatedProduct) {
        // Logic to update the product with the provided data
        // ...

        // Save the updated product
        return productRepository.save(updatedProduct);
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        // Logic to delete the product with the specified ID
        // ...

        // Delete the product from the repository
        productRepository.deleteById(id);
    }
}
