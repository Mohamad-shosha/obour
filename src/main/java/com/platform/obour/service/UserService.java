package com.platform.obour.service;

import com.platform.obour.dto.UserDTO;
import com.platform.obour.entity.User;
import com.platform.obour.entity.enums.Role;
import com.platform.obour.mapper.UserMapper;
import com.platform.obour.repository.UserRepository;
import com.platform.obour.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsersByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getStudents() {
        return getUsersByRole(Role.STUDENT);
    }

    public List<UserDTO> getTeachers() {
        return getUsersByRole(Role.TEACHER);
    }
}
