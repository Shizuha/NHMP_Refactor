package com.minje.nhmpspringboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.*;
@Controller
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping(path = "examples/landing") // 이곳으로 들어오는 API주소를 mapping, examples/landing주소로 받겠다 (localhost:8080/examples/landing)
    public String functionIntro() { 
        log.info("이 페이지로 잘 들어갔나요?");
        return "landing";
    }
}
