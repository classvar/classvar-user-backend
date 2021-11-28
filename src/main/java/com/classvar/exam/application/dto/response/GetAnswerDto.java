package com.classvar.exam.application.dto.response;

import com.classvar.exam.domain.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAnswerDto {

  private GetQuestionDto question;

  private String answer;

  private boolean isCorrect;

  private AnswerStatus answerStatus;
}
