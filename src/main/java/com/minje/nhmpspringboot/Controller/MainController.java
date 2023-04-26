package com.minje.nhmpspringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String functionIntro() {
        return "index"; // 커밋 테스트
    }
}
