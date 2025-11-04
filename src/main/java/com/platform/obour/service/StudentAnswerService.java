package com.platform.obour.service;

import com.platform.obour.dto.StudentAnswerDTO;
import com.platform.obour.dto.SubmitAnswersRequest;
import com.platform.obour.entity.*;
import com.platform.obour.mapper.StudentAnswerMapper;
import com.platform.obour.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ChoiceRepository choiceRepository;
    private final StudentAnswerMapper mapper;

    public StudentAnswerDTO create(StudentAnswerDTO dto) {
        User student = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Question question = questionRepository.findById(dto.questionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Choice choice = choiceRepository.findById(dto.choiceId())
                .orElseThrow(() -> new RuntimeException("Choice not found"));

        StudentAnswer answer = mapper.toEntity(dto, student, question, choice);
        return mapper.toDTO(studentAnswerRepository.save(answer));
    }

    public List<StudentAnswerDTO> getByStudent(Long studentId) {
        return studentAnswerRepository.findByStudent_Id(studentId)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StudentAnswerDTO> getBySectionAndStudent(Long sectionId, Long studentId) {
        return studentAnswerRepository.findByQuestion_Section_IdAndStudent_Id(sectionId, studentId)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StudentAnswerDTO> getAll() {
        return studentAnswerRepository.findAll()
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<StudentAnswerDTO> saveAll(SubmitAnswersRequest request) {
        User student = userRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<StudentAnswer> answers = request.answers().stream().map(a -> {
            Question question = questionRepository.findById(a.questionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            Choice choice = choiceRepository.findById(a.choiceId())
                    .orElseThrow(() -> new RuntimeException("Choice not found"));

            return StudentAnswer.builder()
                    .student(student)
                    .question(question)
                    .choice(choice)
                    .build();
        }).collect(Collectors.toList());

        return studentAnswerRepository.saveAll(answers)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public Integer calculateStudentScore(Long studentId) {
        List<StudentAnswer> answers = studentAnswerRepository.findByStudent_Id(studentId);

        if (answers.isEmpty()) {
            throw new RuntimeException("No answers found for this student");
        }

        return answers.stream()
                .mapToInt(a -> {
                    Integer score = a.getChoice().getScore();
                    return score != null ? score : 0;
                })
                .sum();
    }

    public Integer calculateScoreBySection(Long studentId, Long sectionId) {
        List<StudentAnswer> answers = studentAnswerRepository.findByQuestion_Section_IdAndStudent_Id(sectionId, studentId);

        if (answers.isEmpty()) {
            throw new RuntimeException("No answers found for this student in this section");
        }

        return answers.stream()
                .mapToInt(a -> {
                    Integer score = a.getChoice().getScore();
                    return score != null ? score : 0;
                })
                .sum();
    }

}
