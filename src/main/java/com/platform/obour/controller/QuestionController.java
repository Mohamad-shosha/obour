package com.platform.obour.controller;

import com.platform.obour.dto.CreateQuestionRequest;
import com.platform.obour.dto.QuestionDTO;
import com.platform.obour.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<QuestionDTO>> bySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(questionService.getBySection(sectionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> create(@RequestBody CreateQuestionRequest req) {
        return ResponseEntity.ok(questionService.create(req));
    }
}
