package com.platform.obour.service;

import com.platform.obour.dto.SectionDTO;
import com.platform.obour.entity.Section;
import com.platform.obour.mapper.SectionMapper;
import com.platform.obour.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;

    public List<SectionDTO> getAll() {
        return sectionRepository.findAll()
                .stream()
                .map(sectionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SectionDTO getById(Long id) {
        Section s = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return sectionMapper.toDTO(s);
    }

    public SectionDTO create(SectionDTO dto) {
        Section parent = null;
        if (dto.parentId() != null) {
            parent = sectionRepository.findById(dto.parentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));
        }
        Section s = sectionMapper.toEntity(dto, parent);
        Section saved = sectionRepository.save(s);
        return sectionMapper.toDTO(saved);
    }

    public List<SectionDTO> getRootSections() {
        return sectionRepository.findByParentIsNull()
                .stream()
                .map(sectionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<SectionDTO> getChildrenByParentId(Long parentId) {
        return sectionRepository.findByParentId(parentId)
                .stream()
                .map(sectionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
