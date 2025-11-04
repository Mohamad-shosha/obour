package com.platform.obour.dto;

import java.time.LocalDateTime;

public record StudentAnswerDTO(
        Long id,
        Long studentId,
        Long questionId,
        Long choiceId,
        LocalDateTime createdAt
) { }
