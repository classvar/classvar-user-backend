package com.classvar.answer.domain;

import com.classvar.exam.domain.AnswerStatus;
import com.classvar.exam.domain.Question;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AnswerSheet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "answer_id")
  private Answer answer;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  private String submitAnswer;

  private Integer score;

  private AnswerStatus answerStatus;

  protected AnswerSheet() {}

  public AnswerSheet(String submitAnswer) {
    this.submitAnswer = submitAnswer;
    this.score = 0;
    this.answerStatus = AnswerStatus.ANSWER_CHECKING;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof AnswerSheet)) return false;
    if (this == another) return true;

    return this.id == ((AnswerSheet) another).id;
  }
}
