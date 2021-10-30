package com.classvar.course.domain;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"student_id", "exam_id"}
                )
        }
)
public class StudentExamInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Enumerated(EnumType.STRING)
    private StareStatus status;

    protected StudentExamInfo() {
    }

    public StudentExamInfo(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
        this.status = StareStatus.STARING;  //
    }
}
