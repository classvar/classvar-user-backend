package com.classvar.exam.domain;

import com.classvar.student.domain.Student;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "exam_id"})})
public class StudentExamStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_id")
  private Exam exam;

  @Enumerated(EnumType.STRING)
  private StareStatus status;

  protected StudentExamStatus() {}

  public StudentExamStatus(Student student, Exam exam) {
    this.student = student;
    this.exam = exam;
    this.status = StareStatus.STARING; //
  }
}
