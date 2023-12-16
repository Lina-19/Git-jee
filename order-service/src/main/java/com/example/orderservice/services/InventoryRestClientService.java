package com.example.orderservice.services;


import com.example.orderservice.entiities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryRestClientService {
    @GetMapping("/products/{productId}?projection=fullProduct")
    Product productById(@PathVariable("productId") Long productId);
    @GetMapping("/products?projection=fullProduct")
    public PagedModel<Product> allProducts();
}
