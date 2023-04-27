package com.kissco.ex.user;

import com.kissco.ex.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SiteUserDto{
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String detail_address;
    // private String regDate;

    private List<OrderItem> orderItems;
    private int cartCount;

}
