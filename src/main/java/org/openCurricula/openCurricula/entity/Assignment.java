package org.openCurricula.openCurricula.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "assignment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @Column(name = "week_number")
    private int weekNumber;

    @Column(name = "day_number")
    private int dayNumber;

    @Column(nullable = false, length = 500)
    private String title;

    @Lob
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;
}
