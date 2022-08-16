package com.callor.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //@RequestMapping(value={"","/"}, RequestMethod=GET) 을 의미하는 것이다.
    @GetMapping(value= {"","/"})
    public String home(){
        return "home";
    }
}
