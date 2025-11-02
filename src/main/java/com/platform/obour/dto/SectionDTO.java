package com.platform.obour.dto;

public record SectionDTO(
        Long id,
        String name,
        String type,
        Long parentId
) {}
