package com.platform.obour.mapper;

import com.platform.obour.dto.SectionDTO;
import com.platform.obour.entity.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {

    public SectionDTO toDTO(Section s) {
        return new SectionDTO(
                s.getId(),
                s.getName(),
                s.getType(),
                s.getParent() == null ? null : s.getParent().getId()
        );
    }

    public Section toEntity(SectionDTO dto, Section parent) {
        Section s = Section.builder()
                .name(dto.name())
                .type(dto.type())
                .build();
        s.setParent(parent);
        return s;
    }
}
