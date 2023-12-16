package com.example.customerservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProduct {
    private  Long id;
    private String customerName;
    private String email;
    private String productName;
    private  double price;
    private int quantity;



}
