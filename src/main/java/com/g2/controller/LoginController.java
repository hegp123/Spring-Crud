package com.g2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.model.UserCredential;

@Controller
public class LoginController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error) {
        model.addAttribute("userCredential", new UserCredential());
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/loginCheck")
    public String loginCheck(@ModelAttribute(name = "userCredential") UserCredential userCredential) {
        if (userCredential.getUserName().equalsIgnoreCase("user")
                && userCredential.getPassword().equalsIgnoreCase("user")) {
            return "contacts";
        }
        return "redirect:/login?error";
    }

}
