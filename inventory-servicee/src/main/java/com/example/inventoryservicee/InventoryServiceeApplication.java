package com.example.inventoryservicee;

import com.example.inventoryservicee.entities.Product;
import com.example.inventoryservicee.reposirories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceeApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return  args -> {
            productRepository.save(new Product(null,"HP Computer",900,100));
            productRepository.save(new Product(null,"Ipad",500,50));
            productRepository.save(new Product(null,"keyboard",20,150));
        };
    }
}
