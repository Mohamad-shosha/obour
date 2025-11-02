package com.platform.obour.dto;

public record LoginRequest(
        String email,
        String password
) {}