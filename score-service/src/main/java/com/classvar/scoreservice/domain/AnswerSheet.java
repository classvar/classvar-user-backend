package com.classvar.scoreservice.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
public class AnswerSheet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String question;
  private String answer;
  private int point;

  public AnswerSheet(Integer id, String question, String answer, int point) {
    this.id = id;
    this.question = question;
    this.answer = answer;
    this.point = point;
  }

  protected AnswerSheet() {}

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof AnswerSheet)) return false;
    if (this == another) return true;

    return this.id == ((AnswerSheet) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, question, answer, point);
  }
}
