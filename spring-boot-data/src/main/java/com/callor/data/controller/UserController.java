package com.callor.data.controller;

import com.callor.data.model.UserVO;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute("userVO") UserVO userVO, Model model){
        // input.html 파일의 form tag 에 th:object 항목이 설정됐어서, 빈 VO를 생성해 model 에 담아 template 에 보여주기
        model.addAttribute("user", userVO);
        return "users/input";
    }

    @RequestMapping(value="/join", method = RequestMethod.POST)
    public String join(UserVO userVO){
        log.debug("받은 데이터{}", userVO);
        return "redirect:/";
    }
}
