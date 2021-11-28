package com.classvar.exam.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentInfoDto {
  private String name;

  private String email;

  private String department;

  private String studentId;
}
