package com.kissco.ex.user;

import lombok.extern.slf4j.Slf4j;
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

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update")
    public ResponseEntity<String> signup(@RequestBody SiteUserDto siteUserDto) {

        log.info("User update() ..");

        ResponseEntity<String> entity = null;

        try {
            userService.update(siteUserDto);   // 회원가입 서비스 요청
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody SiteUserDto siteUserDto) {

        log.info("User delete() ..");

        ResponseEntity<String> entity = null;

        try {
            userService.delete(siteUserDto);   // 회원가입 서비스 요청
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
