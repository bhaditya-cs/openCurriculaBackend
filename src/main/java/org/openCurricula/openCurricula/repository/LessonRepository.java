package org.openCurricula.openCurricula.repository;

import org.openCurricula.openCurricula.entity.Curriculum;
import org.openCurricula.openCurricula.entity.Lesson;
import org.openCurricula.openCurricula.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCurriculum (Curriculum curriculum);
    List<Lesson> findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(Long curriculumId);
}
