package com.classvar.scoreservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class GetAnswerDto {
  private Integer id;
  private TempUserDto user;
  private List<GetSubmittedAnswerDto> submittedAnswers;
}
