package com.classvar.exam.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateExamDto {
  @Positive(message = "courseId 필드는 양수 여야 합니다.")
  private long courseId;

  @NotEmpty(message = "name 필드는 비어 있을 수 없습니다.")
  private String name;

  @NotNull(message = "examDate 필드가 존재하지 않습니다.")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate examDate;

  @NotNull(message = "startTime 필드가 존재하지 않습니다.")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
  @DateTimeFormat(pattern = "HH:mm:ss")
  private LocalTime startTime;

  @NotNull(message = "endTime 필드가 존재하지 않습니다.")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
  @DateTimeFormat(pattern = "HH:mm:ss")
  private LocalTime endTime;
}
