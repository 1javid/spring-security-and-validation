package com.example.demo.service;

import com.example.demo.model.entity.Community;

import java.util.List;

public interface CommunityService {
    List<Community> getAllCommunities();

    Community save(Community community);

    Community getById(Long id);

    void deleteById(Long id);
}
