package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(
      mappedBy = "course",
      fetch =
          FetchType
              .LAZY, // courseList findAll() 조회할때 Eager로 설정하면 select course1번 그 안에 있는 exam n번 쿼리가
                     // 나간다.
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private Set<Exam> exams = new HashSet<>();

  public Course(String name) {
    this.name = name;
  }

  protected Course() {}

  public void changeCourseName(String name) {
    this.name = name;
  }

  public void addExam(Exam exam) {
    exam.setCourse(this);
    this.exams.add(exam);
  }

  public void deleteExam(long examId) {
    if (!this.exams.removeIf(exam -> exam.getId() == examId)) {
      throw new IllegalArgumentException("존재하지 않는 시험 입니다.");
    }
  }
}
