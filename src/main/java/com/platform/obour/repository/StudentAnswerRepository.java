package com.platform.obour.repository;

import com.platform.obour.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {

    List<StudentAnswer> findByStudent_Id(Long studentId);

    List<StudentAnswer> findByQuestion_Section_IdAndStudent_Id(Long sectionId, Long studentId);
}
