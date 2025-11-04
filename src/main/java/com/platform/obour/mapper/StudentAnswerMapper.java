package com.platform.obour.mapper;

import com.platform.obour.dto.StudentAnswerDTO;
import com.platform.obour.entity.Choice;
import com.platform.obour.entity.Question;
import com.platform.obour.entity.StudentAnswer;
import com.platform.obour.entity.User;
import org.springframework.stereotype.Component;

@Component
public class StudentAnswerMapper {

    public StudentAnswerDTO toDTO(StudentAnswer entity) {
        return new StudentAnswerDTO(
                entity.getId(),
                entity.getStudent().getId(),
                entity.getQuestion().getId(),
                entity.getChoice().getId(),
                entity.getCreatedAt()
        );
    }

    public StudentAnswer toEntity(StudentAnswerDTO dto, User student, Question question, Choice choice) {
        return StudentAnswer.builder()
                .id(dto.id())
                .student(student)
                .question(question)
                .choice(choice)
                .build();
    }
}
