package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Role;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@SessionAttributes({"user", "roles"})
public class UserController {
    static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin/users")
    public String getUsers(Model model) {

        var users = UserMapper.INSTANCE.userListToUserDtoList(userService.getAllUsers());
        LOGGER.info("getUsers()");

        model.addAttribute("users", users);
        return "admin/main";
    }

    @GetMapping("admin/users/{id}")
    public String getUserRoles(Model model, @PathVariable Long id) {
        var userDto = UserMapper.INSTANCE.userToUserDto(userService.getById(id));
        LOGGER.info("getMapping()");

        List<Role> userRoles = new ArrayList<>();
        List<String> userRolesString = Arrays.stream(userDto.getRoles().split(";")).collect(Collectors.toList());

        for (Role role : userDto.getRolesList()) {
            for (String userRoleString : userRolesString) {
                if (role.getName().equals(userRoleString))
                    userRoles.add(role);
            }
        }

        List<Role> availableRoles = new ArrayList<>();
        for (Role role : userDto.getRolesList()) {
            if (!userRoles.contains(role))
                availableRoles.add(role);
        }

        model.addAttribute("user", userDto);
        model.addAttribute("roles", userRoles);
        model.addAttribute("availableRoles", availableRoles);

        return "user/update";
    }

    @PostMapping("admin/users/{id}")
    public String addUserRoles(Model model,
                               @RequestParam("selectedRole") Role role,
                               @SessionAttribute("user") UserDto userDto) {

        Role selectedRole = null;
        if (role != null) selectedRole = role;
        if (selectedRole != null) userDto.setRoles(userDto.getRoles() + ";" + selectedRole.getName());

        model.addAttribute("user", userDto);
        userService.saveRoleToUser(userDto.getRoles(), userDto.getId());

        return "redirect:/admin/users/{id}";
    }

    @GetMapping("admin/users/{id}/delete/{roleId}")
    public String deleteUserRole(@PathVariable Long id, @PathVariable Long roleId, @SessionAttribute List<Role> roles, Model model) {
        var userDto = UserMapper.INSTANCE.userToUserDto(userService.getById(id));

        String selectedRole = null;
        for (Role role : roles) {
            if (role.getId().equals(roleId)) {
                selectedRole = role.toString();
            }
        }

        if (selectedRole != null) {

            if (!selectedRole.equals("ROLE_USER")) {
                String currentRoles = userDto.getRoles();
                if (currentRoles.startsWith(selectedRole)) {
                    currentRoles = currentRoles.replace(selectedRole + ";", "");
                } else if (currentRoles.endsWith(selectedRole)) {
                    currentRoles = currentRoles.replace(";" + selectedRole, "");
                } else {
                    currentRoles = currentRoles.replace(selectedRole, "");
                }

                userDto.setRoles(currentRoles);
            }
        }

        userService.saveRoleToUser(userDto.getRoles(), id);
        model.addAttribute("user", userDto);

        return "redirect:/admin/users/{id}";
    }
}