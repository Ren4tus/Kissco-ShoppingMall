package com.kissco.ex.domain;

import com.kissco.ex.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "cart")
@Getter @Setter
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "cart",fetch = LAZY)
    private SiteUser user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<OrderItem> cartItems = new ArrayList<>();

    public static void addItem(SiteUser siteUser, OrderItem orderItem) {
        siteUser.getCart().getCartItems().add(orderItem);
    }
}
