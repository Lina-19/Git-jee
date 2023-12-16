package com.example.orderservice.web;

import com.example.orderservice.entiities.Customer;
import com.example.orderservice.entiities.Order;
import com.example.orderservice.entiities.Product;
import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.repositories.ProductItemRepository;
import com.example.orderservice.services.CustomerRestClientService;
import com.example.orderservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController( OrderRepository orderRepository,
     ProductItemRepository productItemRepository,
     CustomerRestClientService customerRestClientService,
     InventoryRestClientService inventoryRestClientService){
        this.customerRestClientService=customerRestClientService;
        this.orderRepository=orderRepository;
        this.inventoryRestClientService=inventoryRestClientService;
        this.productItemRepository=productItemRepository;
    }
    @GetMapping("fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById((order.getCustomerId()));
        order.setCustomer(customer);
        order.getProductItemss().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }

}
