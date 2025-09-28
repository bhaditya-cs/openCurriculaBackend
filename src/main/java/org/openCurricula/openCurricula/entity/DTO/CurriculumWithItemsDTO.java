package org.openCurricula.openCurricula.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumWithItemsDTO {
    private String name;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CurriculumItemDTO> items;
}