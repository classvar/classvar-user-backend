package com.classvar.exam.domain;

import com.classvar.course.domain.ExamTaker;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "exam_id"})})
public class StudentExamInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ExamTaker examTaker;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Exam exam;

  @Enumerated(EnumType.STRING)
  private StareStatus status;

  protected StudentExamInfo() {}

  public StudentExamInfo(ExamTaker examTaker, Exam exam) {
    this.examTaker = examTaker;
    this.exam = exam;
    this.status = StareStatus.STARING; //
  }
}
