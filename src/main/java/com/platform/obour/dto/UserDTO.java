package com.platform.obour.dto;

import com.platform.obour.entity.enums.Role;

public record UserDTO(
        Long id,
        String name,
        String email,
        Role role
) {}
