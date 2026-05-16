package org.example.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.AuthRequest;
import org.example.healthcare.dto.AuthResponse;
import org.example.healthcare.dto.RegisterRequest;
import org.example.healthcare.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
   @PostMapping("/register")
    public ResponseEntity<AuthResponse>register(@Valid @RequestBody RegisterRequest request){
       return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
   }

   @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request){
       return ResponseEntity.ok(authService.login(request));
   }

}
