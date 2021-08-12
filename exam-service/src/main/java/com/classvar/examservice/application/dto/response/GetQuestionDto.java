package com.classvar.examservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetQuestionDto {
  private int id;
  private String name;
  private String type;
  private List<String> description;
  private int point;
}
