package com.minje.nhmpspringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.*;
@Controller
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping(value = "index")
    public String IntroPage() { 
        log.info("index Controller와 잘 연결이 되었나요?");
        return "index";
    }

    @GetMapping(value = "examples/landing")
    public String landingPage() { 
        log.info("landing Controller와 잘 연결이 되었나요?");
        return "examples/landing";
    }

    @GetMapping(value = "examples/profile")
    public String ProfilePage() { 
        log.info("Profile Controller와 잘 연결이 되었나요?");
        return "examples/profile";
    }

    @GetMapping(value = "examples/login")
    public String LoginPage() { 
        log.info("Login Controller와 잘 연결이 되었나요?");
        return "examples/login";
    }

    @GetMapping(value = "examples/register")
    public String RegisterPage() { 
        log.info("Register Controller와 잘 연결이 되었나요?");
        return "examples/register";
    }
}
