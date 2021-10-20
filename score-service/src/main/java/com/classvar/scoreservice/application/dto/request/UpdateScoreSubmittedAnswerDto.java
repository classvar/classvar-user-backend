package com.classvar.scoreservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateScoreSubmittedAnswerDto {
  private Integer id;
  private Integer questionId;
  private int point;
}
