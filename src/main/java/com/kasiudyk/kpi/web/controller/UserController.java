package com.kasiudyk.kpi.web.controller;


import com.kasiudyk.kpi.service.UserService;
import com.kasiudyk.kpi.service.model.User;
import com.kasiudyk.kpi.web.dto.LoginUserDto;
import com.kasiudyk.kpi.web.dto.RegisterUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());

        return "registration";
    }

    //ToDo: add check on email or password already registered
    //ToDo: add mapper from dto to model
    @PostMapping("/registration")
    public String register(Model model,
                           @ModelAttribute("registerUserDto") RegisterUserDto registerUserDto) {
        userService.register(
                new User().setUsername(registerUserDto.getUsername())
                        .setEmail(registerUserDto.getEmail())
                        .setPassword(registerUserDto.getPassword())
        );

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginUserDto", new LoginUserDto());

        return "login";
    }

    //ToDo: add mapper from dto to model
    @PostMapping("/login")
    public String login(Model model, HttpSession httpSession,
                        @ModelAttribute("loginUserDto") LoginUserDto loginUserDto) {
        User user = userService.login(
                new User().setEmail(loginUserDto.getEmail())
                        .setPassword(loginUserDto.getPassword())
        );

        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "login";
        } else {
            httpSession.setAttribute("userId", user.getId());
            return "redirect:/tender/allActiveTenders";
        }
    }
}