package com.classvar.scoreservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScoreDto {
  private Integer id;
  private Integer examId;
  private String name;
  private String email;
  private List<AnswerSheetDto> answers;
}
