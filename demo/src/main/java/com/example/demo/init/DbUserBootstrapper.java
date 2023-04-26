package com.example.demo.init;

import com.example.demo.model.entity.Community;
import com.example.demo.model.entity.User;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbUserBootstrapper {

    @Bean
    public ApplicationRunner initUser(UserRepository userRepo,
                                  PasswordEncoder encoder) {
        return (args) -> {
            User adminUser = new User("admin", encoder.encode("admin"),
                    "admin@ada.edu.az");

            userRepo.save(adminUser.addRole("ROLE_ADMIN"));

            userRepo.save(new User("user", encoder.encode("user"),
                    "user@ada.edu.az"));
        };
    }

    @Bean
    @Autowired
    public CommandLineRunner initCommunity(CommunityRepository communityRepo) {
        return (args) -> {
            Community gitHub = new Community(1L, "GitHub", "Github is an Internet hosting service for software development and version control using Git.");
            Community reddit = new Community(2L, "Reddit", "Reddit is a forum for posting and discussing various topics.");
            Community stackOverflow = new Community(3L, "Stack Overflow", "Stack Overflow is a question and answer website for programmers.");
            Community discord = new Community(4L, "Discord", "Discord is a VoIP and instant messaging social platform.");
            Community twitter = new Community(5L, "Twitter", "Twitter is a free social networking site where users broadcast short posts known as tweets.");
            Community linkedIn = new Community(6L, "LinkedIn", "LinkedIn is a business and employment-focused social media platform that works through websites and mobile apps.");

            communityRepo.save(gitHub);
            communityRepo.save(reddit);
            communityRepo.save(stackOverflow);
            communityRepo.save(discord);
            communityRepo.save(twitter);
            communityRepo.save(linkedIn);
        };
    }
}
