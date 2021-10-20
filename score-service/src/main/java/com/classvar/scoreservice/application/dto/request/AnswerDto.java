package com.classvar.scoreservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerDto {
  private Integer courseId;
  private Integer examId;
  private Integer tempUserId;
  List<SubmittedAnswerDto> submittedAnswers;
}
