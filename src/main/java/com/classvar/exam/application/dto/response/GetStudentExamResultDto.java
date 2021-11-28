package com.classvar.exam.application.dto.response;

import com.classvar.exam.domain.GradingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentExamResultDto {
  private String uuid;

  private String name;

  private String email;

  private String department;

  private GradingStatus gradingStatus;

  private Integer totalScore;
}
