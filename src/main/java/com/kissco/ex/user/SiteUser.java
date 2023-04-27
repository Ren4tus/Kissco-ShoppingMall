package com.kissco.ex.user;

import com.kissco.ex.domain.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id @GeneratedValue()
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(unique = true)
    private String detail_address;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String phone;

    @Column(name = "reg_date")
    private LocalDateTime regDate;


    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderItem> cartItems = new ArrayList<>();
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
    private int cartCount;
    public void addCartItem(OrderItem orderItem) {
        for (OrderItem item : cartItems) {
            if (item.getItem().getId().equals(orderItem.getItem().getId())) {
                item.setCount(item.getCount() + orderItem.getCount());
                cartCount = cartItems.size();
                return;
            }
        }
        cartItems.add(orderItem);
        orderItem.setUser(this);
        cartCount = cartItems.size();
    }
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem item : cartItems) {
            totalPrice += item.getItem().getPrice() * item.getCount();
        }
        return totalPrice;
    }

//    public void setCart(Cart cart) {
//        this.cart = cart;
//        cart.setUser(this);
//        cartCount = 5;
//    }
}