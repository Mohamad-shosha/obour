package com.platform.obour.repository;

import com.platform.obour.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySectionId(Long sectionId);
}
