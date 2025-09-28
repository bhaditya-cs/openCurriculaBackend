package org.openCurricula.openCurricula.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long lessonId;

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
}