package com.classvar.scoreservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubmittedAnswerDto {
  private Integer courseId;
  private Integer examId;
  private Integer tempUserId;
  private Integer questionId;
  private String answer;
}
