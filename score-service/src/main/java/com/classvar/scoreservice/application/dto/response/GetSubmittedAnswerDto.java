package com.classvar.scoreservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetSubmittedAnswerDto {
  private Integer id;
  private TempQuestionDto question;
  private String answer;
}
