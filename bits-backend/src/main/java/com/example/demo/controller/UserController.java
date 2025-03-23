package com.example.demo.controller;

import entities.User;
import com.example.inventory.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Operation(summary = "Get a user by email", description = "Retrieve a user by their email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(
        @Parameter(description = "Email address of the user to retrieve", required = true)
        @PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a user by ID", description = "Retrieve a user by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
        @Parameter(description = "ID of the user to retrieve", required = true)
        @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or duplicate email"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createUser(
        @Parameter(description = "User object to create", required = true)
        @Valid @RequestBody User user) {
        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.status(201).body(savedUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body("Error: Email address already exists");
        }
    }

    @Operation(summary = "Update an existing user", description = "Update a user by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input or duplicate email"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
        @Parameter(description = "ID of the user to update", required = true)
        @PathVariable Long id,
        @Parameter(description = "Updated user object", required = true)
        @Valid @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = existingUser.get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPasswordHash(updatedUser.getPasswordHash());
        user.setRole(updatedUser.getRole());
        user.setCreatedAt(updatedUser.getCreatedAt());

        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(400).body("Error: Email address already exists");
        }
    }

    @Operation(summary = "Delete a user", description = "Delete a user by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @Parameter(description = "ID of the user to delete", required = true)
        @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}