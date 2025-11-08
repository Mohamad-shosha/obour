package com.platform.obour.repository;

import com.platform.obour.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByType(String type);
    List<Section> findByParentIsNull();
    List<Section> findByParentId(Long parentId);
}
