package com.classvar.examservice.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Enumerated(value = EnumType.STRING)
  private QuestionType type;

  @ElementCollection
  @CollectionTable(name = "question_choice")
  private List<QuestionChoice> description;

  private int point;

  protected Question() {}

  public Question(
      Integer id, String name, QuestionType type, List<QuestionChoice> description, int point) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.description = description;
    this.point = point;
  }

  public void updateQuestion(
      String name, QuestionType type, List<QuestionChoice> description, int point) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.point = point;
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
