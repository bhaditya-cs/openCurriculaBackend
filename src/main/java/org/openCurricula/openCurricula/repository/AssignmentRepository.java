package org.openCurricula.openCurricula.repository;

import org.openCurricula.openCurricula.entity.Assignment;
import org.openCurricula.openCurricula.entity.Curriculum;
import org.openCurricula.openCurricula.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCurriculum (Curriculum curriculum);
    List<Assignment> findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(Long curriculumId);
}
