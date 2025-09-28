package org.openCurricula.openCurricula.repository;

import org.openCurricula.openCurricula.entity.Activity;
import org.openCurricula.openCurricula.entity.Curriculum;
import org.openCurricula.openCurricula.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByCurriculum (Curriculum curriculum);
    List<Activity> findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(Long curriculumId);
}
