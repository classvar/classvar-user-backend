package com.classvar.examservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateOrUpdateExamDto {

  private Integer id;

  @NotBlank(message = "시험의 이름은 반드시 있어야 합니다.")
  private String name;

  @NotNull(message = "시험의 시작시간은 반드시 있어야 합니다.")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  private LocalDateTime beginAt;

  @NotNull(message = "시험의 종료시간은 반드시 있어야 합니다.")
  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "Asia/Seoul")
  private LocalDateTime endAt;
}
