package cn.tedu.straw.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/register.html")
    public String register(){
        return "register";
    }

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/question/create.html")
    public String createQuestion(){
        return "question/create";
    }

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }
}
