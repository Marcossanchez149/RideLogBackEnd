package com.ridelog.app.ui.controllers;


import com.ridelog.app.domain.services.UserService;
import com.ridelog.app.ui.models.UserDTO;
import com.ridelog.app.ui.models.UserRegistrationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        try {
            UserDTO user = userService.createUser(request.getName(), request.getEmail(), request.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserDTO>> searchUsers(@RequestParam String name, Pageable pageable) {
        Page<UserDTO> users = userService.searchUsers(name, pageable);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<UserDTO> addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        try {
            UserDTO user = userService.addFriend(userId, friendId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<UserDTO> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        try {
            UserDTO user = userService.removeFriend(userId, friendId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<UserDTO>> getFriends(@PathVariable Long userId) {
        try {
            List<UserDTO> friends = userService.getFriends(userId);
            return ResponseEntity.ok(friends);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{userId}/visited-parks/{parkId}")
    public ResponseEntity<UserDTO> addVisitedPark(@PathVariable Long userId, @PathVariable Long parkId) {
        try {
            UserDTO user = userService.addVisitedPark(userId, parkId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{userId}/wishlist/{parkId}")
    public ResponseEntity<UserDTO> addToWishlist(@PathVariable Long userId, @PathVariable Long parkId) {
        try {
            UserDTO user = userService.addToWishlist(userId, parkId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
