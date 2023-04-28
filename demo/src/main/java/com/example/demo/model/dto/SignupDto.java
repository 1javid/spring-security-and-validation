package com.example.demo.model.dto;

import com.example.demo.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignupDto {

    @NotEmpty(message = "Username can't be empty")
    private String username;
    @NotEmpty(message = "Password can't be empty")
    private String password;
    @Email(regexp = "^(.+)@(.+)\\.(.+)$", message = "Email should follow a standard abc@def.xyz")
    private String email;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password), email);
    }
}
