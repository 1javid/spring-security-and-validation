package com.example.demo.controller;

import com.example.demo.model.entity.Community;
import com.example.demo.service.CommunityService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String save(@Valid @ModelAttribute Community community, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "community/new";

        communityService.save(community);
        return "redirect:/user/communities";
    }

    @GetMapping("communities/{id}/update")
    public String updateCommunity(@PathVariable Long id, Model model) {
        model.addAttribute("community", communityService.getById(id));
        return "community/update";
    }

    @PostMapping("communities/{id}/update")
    public String postCommunity(@PathVariable("id") Long id, @Valid @ModelAttribute("community") Community community, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "community/update";
        } else {
            Community existingCommunity = communityService.getById(id);
            existingCommunity.setName(community.getName());
            existingCommunity.setDescription(community.getDescription());
            communityService.save(existingCommunity);
            return "redirect:/user/communities";
        }
    }

    @GetMapping("communities/{id}/delete")
    public String deleteCommunity(@PathVariable Long id) {
        communityService.deleteById(id);
        return "redirect:/user/communities";
    }
}