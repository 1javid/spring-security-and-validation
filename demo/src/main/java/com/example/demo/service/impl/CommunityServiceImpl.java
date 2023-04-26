package com.example.demo.service.impl;

import com.example.demo.model.entity.Community;
import com.example.demo.repository.CommunityRepository;
import com.example.demo.service.CommunityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    CommunityRepository communityRepo;

    public CommunityServiceImpl(CommunityRepository communityRepo) {
        this.communityRepo = communityRepo;
    }

    @Override
    public List<Community> getAllCommunities() {
        return (List<Community>) communityRepo.getAllCommunitiesUsingJPAQuery();
    }

    @Override
    public Community save(Community community) {
        return communityRepo.save(community);
    }

    @Override
    public Community getById(Long id) {
        return communityRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        communityRepo.deleteById(id);
    }
}
