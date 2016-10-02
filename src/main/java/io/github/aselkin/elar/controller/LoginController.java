package io.github.aselkin.elar.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.aselkin.elar.model.User;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/success")
    public String success(Model model) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User authenticatedUser = (User) auth.getPrincipal();
        model.addAttribute("username", authenticatedUser.getName());
        return "success";
    }
}
