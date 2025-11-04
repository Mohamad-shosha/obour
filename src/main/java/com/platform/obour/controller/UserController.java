package com.platform.obour.controller;

import com.platform.obour.dto.UserDTO;
import com.platform.obour.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/students")
    public ResponseEntity<List<UserDTO>> getStudents() {
        return ResponseEntity.ok(userService.getStudents());
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<UserDTO>> getTeachers() {
        return ResponseEntity.ok(userService.getTeachers());
    }
}
