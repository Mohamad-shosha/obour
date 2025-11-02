package com.platform.obour.dto;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String role
) {}
