package com.classvar.exam.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

  private Integer numberOfProblem; // numberOfQuestions

  @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Question> questions = new HashSet<>();

  @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<StudentExamInfo> studentExamInfos = new HashSet<>();

  public Exam(
      Long courseId, String name, LocalDate examDate, LocalTime startTime, LocalTime endTime) {
    this.courseId = courseId;
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

  public void addQuestion(Question question) {
    question.setExam(this);
    this.questions.add(question);
  }

  public void updateQuestion(Long questionId, Question updatePayload) {
    for (Question question : this.questions) {
      if (question.getId() == questionId) {
        question.updateQuestionInfo(updatePayload);
        return;
      }
    }
    throw new IllegalArgumentException("존재하지 않는 문제 입니다.");
  }

  public void deleteQuestion(Long idToDelete) {
    if (!this.questions.removeIf(question -> question.getId() == idToDelete)) {
      throw new IllegalArgumentException("존재하지 않는 문제 입니다.");
    }
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
