package com.classvar.course.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDate examDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private Integer numberOfProblem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_Id")
  private Course course;

  public Exam(String name, LocalDate examDate, LocalTime startTime, LocalTime endTime) {
    this.name = name;
    this.examDate = examDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  protected Exam() {}

  public void setCourse(Course course) {
    this.course = course;
  }

  public void updateExamInfo(
      String name, LocalDate examDate, LocalTime startTime, LocalTime endTime) {
    this.name = name;
    this.examDate = examDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}