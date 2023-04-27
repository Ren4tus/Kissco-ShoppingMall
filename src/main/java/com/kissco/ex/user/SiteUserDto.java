package com.kissco.ex.user;

import com.kissco.ex.domain.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String detail_address;
    // private String regDate;

    private Cart cart;
}
