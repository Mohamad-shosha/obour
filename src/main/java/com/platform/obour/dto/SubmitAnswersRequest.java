package com.platform.obour.dto;

import java.util.List;

public record SubmitAnswersRequest(
        Long studentId,
        List<AnswerItem> answers
) {
    public record AnswerItem(Long questionId, Long choiceId) {}
}
