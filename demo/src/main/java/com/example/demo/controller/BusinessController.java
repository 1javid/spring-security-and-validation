package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessController {

    @GetMapping("/admin")
    public String getAdminsHomePage(Model model) {
        model.addAttribute("message", "This is home for admins");
        return "admin/panel";
    }

    @GetMapping("/user")
    public String getUsersHomePage(Model model) {
        model.addAttribute("message", "This is home for users");
        return "user/panel";
    }
}
