package com.samir.librarymanagement.controller;

import com.samir.librarymanagement.dto.User.UserRequest;
import com.samir.librarymanagement.dto.User.UserResponse;
import com.samir.librarymanagement.entity.User;
import com.samir.librarymanagement.service.User.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest)
    {
        UserResponse userResponse = userService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

    }
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserRequest userRequest)
    {
        userService.login(userRequest);
        return ResponseEntity.noContent().build();
    }
}
