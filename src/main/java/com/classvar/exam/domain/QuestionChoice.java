package com.classvar.exam.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class QuestionChoice {
  private String content;

  public QuestionChoice(String content) {
    this.content = content;
  }

  protected QuestionChoice() {}
}
