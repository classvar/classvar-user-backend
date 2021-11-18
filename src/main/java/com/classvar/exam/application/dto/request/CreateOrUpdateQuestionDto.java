package com.classvar.exam.application.dto.request;

import com.classvar.exam.domain.QuestionChoice;
import com.classvar.exam.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateQuestionDto {

  @NotEmpty(message = "name 필드는 비어 있을 수 없습니다.")
  private String name;

  @NotEmpty(message = "type 필드는 비어 있을 수 없습니다.")
  private QuestionType type;

  private List<QuestionChoice> description;

  @NotEmpty(message = "point 필드는 비어 있을 수 없습니다.")
  private int point;
}
