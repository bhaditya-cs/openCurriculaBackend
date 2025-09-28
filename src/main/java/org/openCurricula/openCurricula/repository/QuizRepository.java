package org.openCurricula.openCurricula.repository;

import org.openCurricula.openCurricula.entity.Curriculum;
import org.openCurricula.openCurricula.entity.Quiz;
import org.openCurricula.openCurricula.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCurriculum (Curriculum curriculum);
    List<Quiz> findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(Long curriculumId);
}
