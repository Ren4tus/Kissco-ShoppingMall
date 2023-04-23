package com.kissco.ex.user;

import com.kissco.ex.domain.Address;
import com.kissco.ex.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}