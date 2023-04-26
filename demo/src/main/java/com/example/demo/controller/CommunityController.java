package com.example.demo.controller;

import com.example.demo.model.entity.Community;
import com.example.demo.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/")
public class CommunityController {
    static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("communities")
    public String getCommunities(Model model){
        List<Community> communities = communityService.getAllCommunities();
        LOGGER.info("getCommunities()");

        model.addAttribute("communities", communities);
        return "community/index";
    }

    @GetMapping("communities/new")
    public String createNewCommunity(Model model) {
        model.addAttribute("community", new Community());
        LOGGER.info("createNewCommunity()");

        return "community/new";
    }

    @PostMapping("communities/new")
    public String save(@ModelAttribute("community") Community community) {
        communityService.save(community);
        return "redirect:/user/communities";
    }

    @GetMapping("communities/{id}/update")
    public String updateCommunity(@PathVariable Long id, Model model) {
        model.addAttribute("community", communityService.getById(id));
        return "community/update";
    }

    @PostMapping("communities/{id}/update")
    public String postCommunity(Model model, @ModelAttribute Community community, Long id) {
        model.addAttribute("community", community);
        communityService.save(community);
        return "redirect:/user/communities";
    }

    @GetMapping("communities/{id}/delete")
    public String deleteCommunity(@PathVariable Long id) {
        communityService.deleteById(id);
        return "redirect:/user/communities";
    }
}