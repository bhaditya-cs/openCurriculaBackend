package org.openCurricula.openCurricula.repository;

import org.openCurricula.openCurricula.entity.Curriculum;
import org.openCurricula.openCurricula.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByCurriculum (Curriculum curriculum);
    List<Test> findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(Long curriculumId);
}
