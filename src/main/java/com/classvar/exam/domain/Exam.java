package com.classvar.exam.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Entity
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long courseId;

  private String name;

  private LocalDate examDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private Integer numberOfProblem;


  public Exam(Long courseId, String name, LocalDate examDate, LocalTime startTime, LocalTime endTime) {
    this.courseId =courseId;
    this.name = name;
    this.examDate = examDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  protected Exam() {}


  public void updateExamInfo(
      String name, LocalDate examDate, LocalTime startTime, LocalTime endTime) {
    this.name = name;
    this.examDate = examDate;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof Exam)) return false;
    if (this == another) return true;

    return this.id == ((Exam) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.examDate, this.startTime, this.endTime);
  }
}
