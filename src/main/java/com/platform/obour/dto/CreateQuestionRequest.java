package com.platform.obour.dto;

import java.util.List;

public record CreateQuestionRequest(
        Long sectionId,
        String text,
        String answerType,
        Long correctChoiceId, // optional (can be null)
        List<CreateChoiceRequest> choices
) {}
