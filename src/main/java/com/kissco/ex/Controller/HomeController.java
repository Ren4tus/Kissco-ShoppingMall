package com.kissco.ex.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@Slf4j
public class HomeController {
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "redirect:/orders";
    }
    @RequestMapping("/admin")
    public String adminPage() {
        log.info("ADMIN PAGE");
        return "adminPage";
    }
    @RequestMapping("/test")
    public String test() {
        log.info("test home");
        return "ongoing/homeIndex";
    }
}