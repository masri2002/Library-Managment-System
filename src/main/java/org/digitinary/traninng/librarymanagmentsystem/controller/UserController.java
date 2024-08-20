package org.digitinary.traninng.librarymanagmentsystem.controller;

import jakarta.validation.Valid;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
     @GetMapping
       public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
     }
     @PostMapping
     public ResponseEntity<?> addUser(@Valid @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.addUser(user));
     }
}
