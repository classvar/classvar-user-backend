package com.classvar.scoreservice.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
public class SubmittedAnswer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Embedded TempQuestion tempQuestion;
  private String answer;
  private int score;

  public SubmittedAnswer(TempQuestion tempQuestion, String answer, int score) {
    this.tempQuestion = tempQuestion;
    this.answer = answer;
    this.score = score;
  }

  protected SubmittedAnswer() {}

  public void changeScore(int score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof TempQuestion)) return false;
    if (this == another) return true;

    return this.id == ((SubmittedAnswer) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionId, answer);
  }
}
