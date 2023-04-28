package com.example.demo.controller;

import com.example.demo.model.dto.SignupDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String showSignUp(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }

    @PostMapping
    public String doSignUp(@Valid @ModelAttribute SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "signup";

        User user = signupDto.toUser(passwordEncoder);
        userRepo.save(user);

        return "redirect:/login";
    }

}
