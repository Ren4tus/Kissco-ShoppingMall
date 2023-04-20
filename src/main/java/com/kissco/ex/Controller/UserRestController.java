package com.kissco.ex.Controller;

import com.kissco.ex.service.UserService;
import com.kissco.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserVO usersVO) {

        log.info("User signup() ..");

        ResponseEntity<String> entity = null;

        System.out.println(usersVO);

        try {
            userService.join(usersVO);   // 회원가입 서비스 요청
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
