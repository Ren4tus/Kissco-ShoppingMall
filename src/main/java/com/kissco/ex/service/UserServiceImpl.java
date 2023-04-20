package com.kissco.ex.service;

import com.kissco.ex.mapper.UserMapper;
import com.kissco.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public void join(UserVO usersVO) {

        log.info("join() ..");

        usersVO.setPassword(new BCryptPasswordEncoder().encode(usersVO.getPassword()));

        userMapper.insertUser(usersVO);
        userMapper.insertAuthorities();
    }
}
