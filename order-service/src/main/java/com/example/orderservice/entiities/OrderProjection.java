package com.example.orderservice.entiities;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name="fullOrder",types = Order.class)
public interface OrderProjection {
    public   Long getId();
    public Date getCreatedAt();
    public OrderStatus getStatus();
    public   Long getCustomerId();

    public Customer getCustomer();


    public List<ProductItem> getProductItemss();
}
