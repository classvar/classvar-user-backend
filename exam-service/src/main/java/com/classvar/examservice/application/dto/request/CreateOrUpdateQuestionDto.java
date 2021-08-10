package com.classvar.examservice.application.dto.request;

import com.classvar.examservice.domain.QuestionType;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOrUpdateQuestionDto {

  private Integer id;

  @NotBlank(message = "문제 이름은 반드시 존재해야 합니다.")
  private String name;

  @NotNull(message = "문제 유형은 반드시 존재해야 합니다.")
  private QuestionType type;

  @Nullable private List<String> description;

  @Min(value = 1, message = "문제 배점은 1점 이상이어야 합니다.")
  private int point;
}
