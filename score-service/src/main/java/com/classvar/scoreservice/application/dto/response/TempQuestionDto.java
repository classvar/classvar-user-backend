package com.classvar.scoreservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TempQuestionDto {
  private int id;
  private String name;
  private String type;
  private List<String> description;
  private int point;
}
