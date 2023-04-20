package com.kissco.ex.mapper;

import com.kissco.ex.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (NO, ADDRESS_NO, ID, PASSWORD, NAME, PHONE, EMAIL)\n" +
            "VALUES (user_no_seq.nextval, #{addressNo}, #{id}, #{password}, #{name}, #{phone}, #{email})")
    void insertUser(UserVO UsersVO);
    @Insert("insert into authorities(user_no) values(user_no_seq.currval)")
    void insertAuthorities();
}
