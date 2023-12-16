package com.example.customerservice.web;

import com.example.customerservice.configurations.ApplicationPropertiesConfiguration;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.entities.CustomerProduct;
import com.example.customerservice.entities.Product;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private final ApplicationPropertiesConfiguration applicationPropertiesConfiguration;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestTemplate restTemplate;


    public CustomerController(ApplicationPropertiesConfiguration applicationPropertiesConfiguration) {
        this.applicationPropertiesConfiguration=applicationPropertiesConfiguration;
    }

    @RequestMapping("/customers")
    public List<Customer> getCustomers(){
        List<Customer> customers=customerRepository.findAll();
        List<Customer> limitCustomers=customers.subList(0,applicationPropertiesConfiguration.getLimitCustomers());
        return limitCustomers;
    }
    @RequestMapping("/getCustomerProducts/{id}")
    public CustomerProduct getCustomerProducts(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        try {
            String inventoryServiceUrl = "http://localhost:8888/INVENTORY-SERVICE/products/" + customer.get().getId();
            Product product = restTemplate.getForObject(inventoryServiceUrl, Product.class);

            return new CustomerProduct(customer.get().getId(), customer.get().getName(), customer.get().getEmail(), product.getName(), product.getPrice(),product.getQuantity());
        } catch (HttpClientErrorException e) {
            // Log the exception for debugging
            e.printStackTrace();
            throw e;
        }
    }


    @RequestMapping("/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Long id){
        return customerRepository.findById(id);
    }
}
