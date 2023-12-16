package com.example.orderservice.services;

import com.example.orderservice.entiities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
@CrossOrigin(origins = "http://localhost:4200")

public interface CustomerRestClientService {

    @GetMapping("/customers/{customerId}?projection=fullCustomer")
    Customer customerById(@PathVariable("customerId") Long customerId);

    @GetMapping("/customers?projection=fullCustomer")
    PagedModel<Customer> allCustomers();
}
