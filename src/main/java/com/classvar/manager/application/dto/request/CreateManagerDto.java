package com.classvar.manager.application.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateManagerDto {

  @NotNull(message = "courseId 필드는 비어 있을 수 없습니다.")
  private Long courseId;

  private List<String> emails;
}
