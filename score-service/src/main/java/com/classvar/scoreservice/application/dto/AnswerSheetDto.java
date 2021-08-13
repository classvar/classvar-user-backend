package com.classvar.scoreservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerSheetDto {
  private Integer id;
  private String question;
  private String answer;
  private int point;
}
