package com.classvar.examservice.application.dto.response;

import com.classvar.examservice.domain.QuestionChoice;
import com.classvar.examservice.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetQuestionDto {
  private int id;
  private String name;
  private QuestionType type;
  private List<QuestionChoice> description;
  private int point;
}
