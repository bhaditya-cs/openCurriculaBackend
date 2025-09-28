package org.openCurricula.openCurricula.service;

import org.openCurricula.openCurricula.entity.*;
import org.openCurricula.openCurricula.entity.DTO.CurriculumItemDTO;
import org.openCurricula.openCurricula.entity.DTO.CurriculumWithItemsDTO;
import org.openCurricula.openCurricula.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CurriculumService {

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    TestRepository testRepository;


    public Optional<List<Curriculum>> getCurricula () {
        List<Curriculum> curricula = curriculumRepository.findAll();
        System.out.println(curricula);
        return Optional.of(curricula);
    }

    public Optional<Curriculum> getById (Long id) {
        Curriculum curriculum = curriculumRepository.findById(id).orElse(null);
        if (curriculum == null) {
            return Optional.empty();
        }
        return Optional.of(curriculum);
    }

    @Transactional(readOnly = true)
    public List<CurriculumItemDTO> getAllItemsForCurriculum(Long curriculumId) {

        List<CurriculumItemDTO> items = new ArrayList<>();

        lessonRepository.findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(curriculumId)
                .forEach(l -> items.add(new CurriculumItemDTO(
                        "Lesson", l.getLessonId(), l.getTitle(), l.getDescription(), l.getWeekNumber(), l.getDayNumber()
                )));

        activityRepository.findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(curriculumId)
                .forEach(a -> items.add(new CurriculumItemDTO(
                        "Activity", a.getActivityId(), a.getTitle(), a.getDescription(), a.getWeekNumber(), a.getDayNumber()
                )));

        assignmentRepository.findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(curriculumId)
                .forEach(a -> items.add(new CurriculumItemDTO(
                        "Assignment", a.getAssignmentId(), a.getTitle(), a.getDescription(), a.getWeekNumber(), a.getDayNumber()
                )));

        quizRepository.findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(curriculumId)
                .forEach(q -> items.add(new CurriculumItemDTO(
                        "Quiz", q.getQuizId(), q.getTitle(), q.getDescription(), q.getWeekNumber(), q.getDayNumber()
                )));

        testRepository.findByCurriculumCurriculumIdOrderByWeekNumberAscDayNumberAsc(curriculumId)
                .forEach(t -> items.add(new CurriculumItemDTO(
                        "Test", t.getTestId(), t.getTitle(), t.getDescription(), t.getWeekNumber(), t.getDayNumber()
                )));

        // Sort by weekNumber then dayNumber
        items.sort(Comparator.comparingInt(CurriculumItemDTO::getWeekNumber)
                .thenComparingInt(CurriculumItemDTO::getDayNumber));

        return items;
    }

    @Transactional
    public Curriculum createCurriculumWithItems(CurriculumWithItemsDTO dto) {
        //create curriculum
        Curriculum curriculum = Curriculum.builder()
                .name(dto.getName())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        curriculum = curriculumRepository.save(curriculum);
        //create items
        if (dto.getItems() != null) {
            for (CurriculumItemDTO item : dto.getItems()) {
                switch (item.getItemType()) {
                    case "Lesson" -> lessonRepository.save(Lesson.builder()
                            .curriculum(curriculum)
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .weekNumber(item.getWeekNumber())
                            .dayNumber(item.getDayNumber())
                            .build());
                    case "Activity" -> activityRepository.save(Activity.builder()
                            .curriculum(curriculum)
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .weekNumber(item.getWeekNumber())
                            .dayNumber(item.getDayNumber())
                            .build());
                    case "Assignment" -> assignmentRepository.save(Assignment.builder()
                            .curriculum(curriculum)
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .weekNumber(item.getWeekNumber())
                            .dayNumber(item.getDayNumber())
                            .build());
                    case "Quiz" -> quizRepository.save(Quiz.builder()
                            .curriculum(curriculum)
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .weekNumber(item.getWeekNumber())
                            .dayNumber(item.getDayNumber())
                            .build());
                    case "Test" -> testRepository.save(Test.builder()
                            .curriculum(curriculum)
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .weekNumber(item.getWeekNumber())
                            .dayNumber(item.getDayNumber())
                            .build());
                    default -> throw new IllegalArgumentException("Unknown item type: " + item.getItemType());
                }
            }
        }

        return curriculum;
    }
}
