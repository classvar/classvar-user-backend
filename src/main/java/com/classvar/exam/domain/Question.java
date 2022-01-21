package com.classvar.exam.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(value = EnumType.STRING)
  private QuestionType type;

  @ElementCollection
  @CollectionTable(name = "question_choice")
  private List<QuestionChoice> choices;

  private int point;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_id")
  private Exam exam;

  protected Question() {}

  public Question(
          Long id, String name, QuestionType type, List<QuestionChoice> choices, int point) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.choices = choices;
    this.point = point;
  }

  public void updateQuestionInfo(Question updatePayload) {
    this.name = updatePayload.getName();
    this.type = updatePayload.getType();
    this.choices = updatePayload.getChoices();
    this.point = updatePayload.getPoint();
  }

  public void setExam(Exam exam) {
    this.exam = exam;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof Question)) return false;
    if (this == another) return true;

    return this.id == ((Question) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, point);
  }
}
