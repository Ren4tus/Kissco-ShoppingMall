package com.kissco.ex.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private int no;
    private int addressNo;
    private String id;
    private String password;
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;
    private String email;
    private String phone;
    private String regDate;
    private char delFlag;

    private List<AuthorityVO> authorityList;
}
