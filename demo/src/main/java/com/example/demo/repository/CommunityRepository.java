package com.example.demo.repository;

import com.example.demo.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Query("select c from Community c")
    Iterable<Community> getAllCommunitiesUsingJPAQuery();
}
