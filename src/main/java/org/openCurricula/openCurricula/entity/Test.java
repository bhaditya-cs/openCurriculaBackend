package org.openCurricula.openCurricula.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;

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

    @Column(name = "total_points")
    private int totalPoints;
}
