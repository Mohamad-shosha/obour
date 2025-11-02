package com.platform.obour.controller;

import com.platform.obour.dto.SectionDTO;
import com.platform.obour.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {
    private final SectionService sectionService;

    @GetMapping
    public ResponseEntity<List<SectionDTO>> all() {
        return ResponseEntity.ok(sectionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(sectionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SectionDTO> create(@RequestBody SectionDTO dto) {
        return ResponseEntity.ok(sectionService.create(dto));
    }
}
