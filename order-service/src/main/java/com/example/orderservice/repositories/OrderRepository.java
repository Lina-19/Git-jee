package com.example.orderservice.repositories;

import com.example.orderservice.entiities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestResource

public interface OrderRepository extends JpaRepository<Order,Long> {
    @RestResource(path = "byCustomerId")
    List<Order> findByCustomerId(@PathVariable("customerId") Long customerId);
}
