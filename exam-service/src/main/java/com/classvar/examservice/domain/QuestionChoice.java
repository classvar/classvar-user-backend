package com.classvar.examservice.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/*
이 클래스는 문제의 선택지 하나 단위를 나타냅니다.
 */
// DTO에서 알아서 { content: String } => String 바꿔주기.
@Embeddable
@Getter
public class QuestionChoice {
  private String content;

  public QuestionChoice(String content) {
    this.content = content;
  }

  protected QuestionChoice() {}
}
