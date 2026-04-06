package com.klu.controller;

import com.klu.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> me(Authentication authentication) {
        return ResponseEntity.ok(new ApiResponse("Logged in as: " + authentication.getName()));
    }
}
