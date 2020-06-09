package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.CreateUserDTO;
import com.taskmanager.taskmanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/signup")
        public String signup(Model model) {
            model.addAttribute("user", new CreateUserDTO());
            return "signupform";
        }

        /*
        @PostMapping("/logout")
        public String logout(Model model) {
            model.addAttribute("user", null);
            return "signinform";
        }
        */






        @PostMapping("/signup")
        public String postCreateUser(@ModelAttribute("user") @Valid CreateUserDTO user, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "signupform";
            }
            userService.createUser(user);
            return "redirect:/login";
        }

        @GetMapping("/login")
        public String getLoginForm() {
            System.out.println("@@@@@@@@@@@@@@@@@@@@");
            return "signinform";
        }
    }


