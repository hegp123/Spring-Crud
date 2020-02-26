package com.g2.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2.constant.ViewContant;
import com.g2.model.UserCredential;

@Controller
public class LoginController {

    private static final Log LOGGER = LogFactory.getLog(LoginController.class);

    @GetMapping("/")
    public String redirectToLogin() {
        LOGGER.info("METHOD: redirectToLogin()");
        return "redirect:/" + ViewContant.LOGIN;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {

        LOGGER.info("METHOD: showLoginForm() -- params: error: " + error + ", logout: " + logout);

        model.addAttribute("userCredential", new UserCredential());
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        LOGGER.info("Returning to login view");
        return ViewContant.LOGIN;
    }

    @PostMapping("/loginCheck")
    public String loginCheck(@ModelAttribute(name = "userCredential") UserCredential userCredential) {

        LOGGER.info("METHOD: loginCheck() -- params: " + userCredential.toString());
        
        if (userCredential.getUserName().equalsIgnoreCase("user")
                && userCredential.getPassword().equalsIgnoreCase("user")) {
            LOGGER.info("Returning to contacts view");
//            return ViewContant.CONTACTS;
            return "redirect:/contact/showcontacts";
        }
        LOGGER.info("Returning to login error");
        return "redirect:/" + ViewContant.LOGIN+"?error";
    }

}
