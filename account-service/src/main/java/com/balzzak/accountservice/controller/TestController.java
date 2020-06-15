package com.balzzak.accountservice.controller;

import com.balzzak.accountservice.model.UserDto;
import com.balzzak.accountservice.model.User;
import com.balzzak.accountservice.model.CustomUserDetails;
import com.balzzak.accountservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "Hello world!";
    }

    @PostMapping("/sign-up")
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createNewUser(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) throws Exception {
        return userService.getToken(userDto);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/user")
    public String user() {
        return "Hello user!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello admin!";
    }

}
