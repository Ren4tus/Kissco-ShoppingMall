package com.kissco.ex.user;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Update("UPDATE SITE_USER SET DETAIL_ADDRESS = #{detail_address}, EMAIL = #{email}, NAME = #{name}, PHONE = #{phone}\n" +
            "WHERE USERNAME = #{username}")
    void updateUser(SiteUserDto siteUserDto);

    @Delete("DELETE FROM SITE_USER WHERE USERNAME = #{username}")
    void deleteUser(SiteUserDto siteUserDto);
}
