package com.example.sms.dto;

import com.example.sms.model.Role;
import jakarta.validation.constraints.*;

public class AuthDtos {
    public record SignupRequest(
            @NotBlank String username,
            @NotBlank String password,
            @NotNull Role role,
            @NotBlank String fullName,
            @Email String email
    ) { }

    public record LoginRequest(
            @NotBlank String username,
            @NotBlank String password
    ) { }

    public record AuthResponse(String token, String username, Role role) { }
}
