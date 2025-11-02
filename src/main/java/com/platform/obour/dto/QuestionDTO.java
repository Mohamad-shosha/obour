package com.platform.obour.dto;

import java.util.List;

public record QuestionDTO(
        Long id,
        Long sectionId,
        String text,
        String answerType,
        Long correctChoiceId,
        List<ChoiceDTO> choices
) {}
