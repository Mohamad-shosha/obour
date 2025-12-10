package com.platform.obour.controller;

import com.platform.obour.dto.StudentAnswerDTO;
import com.platform.obour.dto.SubmitAnswersRequest;
import com.platform.obour.service.StudentAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-answers")
@RequiredArgsConstructor
public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    @PostMapping("/submit")
    public ResponseEntity<List<StudentAnswerDTO>> submitAnswers(@RequestBody SubmitAnswersRequest request) {
        return ResponseEntity.ok(studentAnswerService.saveAll(request));
    }

    @PostMapping
    public ResponseEntity<StudentAnswerDTO> createStudentAnswer(@RequestBody StudentAnswerDTO dto) {
        StudentAnswerDTO created = studentAnswerService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<StudentAnswerDTO>> getAllAnswers() {
        List<StudentAnswerDTO> allAnswers = studentAnswerService.getAll();
        return ResponseEntity.ok(allAnswers);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentAnswerDTO>> getAnswersByStudent(@PathVariable Long studentId) {
        List<StudentAnswerDTO> answers = studentAnswerService.getByStudent(studentId);
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/section/{sectionId}/student/{studentId}")
    public ResponseEntity<List<StudentAnswerDTO>> getAnswersBySectionAndStudent(
            @PathVariable Long sectionId,
            @PathVariable Long studentId) {

        List<StudentAnswerDTO> answers = studentAnswerService.getBySectionAndStudent(sectionId, studentId);
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/score/{studentId}")
    public ResponseEntity<Integer> getStudentScore(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentAnswerService.calculateStudentScore(studentId));
    }

    @GetMapping("/score/{studentId}/section/{sectionId}")
    public ResponseEntity<Integer> getScoreBySection(@PathVariable Long studentId, @PathVariable Long sectionId) {
        return ResponseEntity.ok(studentAnswerService.calculateScoreBySection(studentId, sectionId));
    }
}
