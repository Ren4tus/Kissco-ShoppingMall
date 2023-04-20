package com.kissco.ex.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
}
