package com.example.travelproject.domain.mainpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.travelproject.domain.user.model.UserEntity;
import com.example.travelproject.domain.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    // http://localhost:8080/
    @GetMapping("/")
    public RedirectView redirectToMain() {
        return new RedirectView("/main");
    }

    // http://localhost:8080/main
    @GetMapping("/main")
    public String mainIndex(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());

        }
        model.addAttribute("menuTitle", "홈");
        return "index";
    }

    // join 화면: http://localhost:8080/joinPage
    @GetMapping("/joinPage")
    public String joinPage() {
        return "login/join";
    }

    // join 요청 -> login 화면으로 이동
    @PostMapping("/join")
    public String join(@ModelAttribute UserEntity dto) {
        log.info("[MainController][join] dto >" + dto.toString());
        userService.joinUserDto(dto);
        return "redirect:/login/login";
    }

    // login 화면: http://localhost:8080/loginPage
    @GetMapping("/loginPage")
    public String loginPage() {
        return "login/login";
    }

    // login 요청 -> 성공화면, 가입자 화면
    // @PostMapping("/login")
    // public String login(@ModelAttribute UserEntity dto, HttpServletRequest
    // request) {
    // UserEntity user = userService.loginUser(dto);

    // }

}
