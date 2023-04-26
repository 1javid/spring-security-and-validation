package com.example.demo.service;

import com.example.demo.model.entity.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    List<User> getAllUsers();

    void saveRoleToUser(String roles, Long userId);
}