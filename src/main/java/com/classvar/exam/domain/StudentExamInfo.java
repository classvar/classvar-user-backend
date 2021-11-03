package com.classvar.exam.domain;

import com.classvar.student.domain.Student;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "exam_id"})})
public class StudentExamInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
