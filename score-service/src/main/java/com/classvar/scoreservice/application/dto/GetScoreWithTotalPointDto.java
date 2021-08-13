package com.classvar.scoreservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetScoreWithTotalPointDto {
  private String name;
  private String email;
  private List<AnswerSheetDto> answers;
  private int totalScore;
}
