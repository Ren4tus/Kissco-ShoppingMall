package com.kissco.ex.Controller;

import com.kissco.ex.service.AddressService;
import com.kissco.ex.vo.AddressVO;
import com.kissco.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final AddressService addressService;

    public UserController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/signup_view")
    public String signupView(Model model) {

        log.info("signup_view() ..");

        List<AddressVO> sidoNameList = new ArrayList<>();
        sidoNameList = addressService.sidoNameList();

        model.addAttribute("sidoNameList", sidoNameList);
        model.addAttribute("memberForm", new UserVO());

        return "signup_view";
    }
}
