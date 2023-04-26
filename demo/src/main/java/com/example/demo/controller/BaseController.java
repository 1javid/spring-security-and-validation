package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @Autowired
    @Qualifier("greetText")
    private String welcomeMessage;

    @Autowired
    @Qualifier("byeText")
    private String farewellMessage;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @GetMapping("/")
    public String getWelcomePage(Model model){
        model.addAttribute("message", welcomeMessage);
        if(userDetailsService.loadUserByUsername(SecurityConfig.currentUser()).getAuthorities().toString().contains("ROLE_ADMIN")) {
            SecurityConfig.nullUser();
            return "index_admin";
        }
        SecurityConfig.nullUser();
        return "index_user";
    }

    @GetMapping("/bye")
    public String getFarewellPage(Model model){
        model.addAttribute("message", farewellMessage);
        return "index_user";
    }
}
