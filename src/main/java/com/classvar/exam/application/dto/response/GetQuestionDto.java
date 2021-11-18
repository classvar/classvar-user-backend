package com.classvar.exam.application.dto.response;

import com.classvar.exam.domain.QuestionChoice;
import com.classvar.exam.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionDto {
  private long id;

  private String name;

  private QuestionType type;

  private List<QuestionChoice> description;

  private int point;
}
