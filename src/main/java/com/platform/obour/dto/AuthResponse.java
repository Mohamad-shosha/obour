package com.platform.obour.dto;

public record AuthResponse(
        String token,
        String name,
        String role
) {}