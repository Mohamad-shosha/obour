package com.platform.obour.mapper;

import com.platform.obour.dto.ChoiceDTO;
import com.platform.obour.dto.CreateChoiceRequest;
import com.platform.obour.dto.CreateQuestionRequest;
import com.platform.obour.dto.QuestionDTO;
import com.platform.obour.entity.Choice;
import com.platform.obour.entity.Question;
import com.platform.obour.entity.Section;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public QuestionDTO toDTO(Question q) {
        List<ChoiceDTO> choiceDTOS = q.getChoices() == null ? List.of() :
                q.getChoices().stream()
                        .map(c -> new ChoiceDTO(c.getId(), c.getText(), c.getScore()))
                        .collect(Collectors.toList());

        return new QuestionDTO(
                q.getId(),
                q.getSection().getId(),
                q.getText(),
                q.getAnswerType(),
                q.getCorrectChoiceId(),
                choiceDTOS
        );
    }

    public Question toEntity(CreateQuestionRequest req, Section section) {
        return Question.builder()
                .section(section)
                .text(req.text())
                .answerType(req.answerType())
                .correctChoiceId(req.correctChoiceId())
                .build();
    }

    public List<Choice> toChoiceEntities(List<CreateChoiceRequest> choiceRequests, Question question) {
        return choiceRequests.stream()
                .map(c -> Choice.builder()
                        .question(question)
                        .text(c.text())
                        .score(c.score())
                        .build())
                .collect(Collectors.toList());
    }

}
