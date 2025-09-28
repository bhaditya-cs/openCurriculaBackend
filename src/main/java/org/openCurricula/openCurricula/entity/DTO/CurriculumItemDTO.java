package org.openCurricula.openCurricula.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurriculumItemDTO {
    private String itemType;      // Lesson, Activity, Assignment, Quiz, Test
    private Long itemId;
    private String title;
    private String description;
    private int weekNumber;
    private int dayNumber;
}