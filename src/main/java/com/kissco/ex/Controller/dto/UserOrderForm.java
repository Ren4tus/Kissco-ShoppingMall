package com.kissco.ex.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderForm
{
    Long id;
    private String username;
    private String productName;
    private int orderCount;
    private int totalPrice;
}
