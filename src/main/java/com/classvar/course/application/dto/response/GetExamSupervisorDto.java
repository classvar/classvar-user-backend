package com.classvar.course.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetExamSupervisorDto {
  private long id;
  private String name;
  private String department;
  private String managerId;
  private String email;
  private boolean approved;
}
