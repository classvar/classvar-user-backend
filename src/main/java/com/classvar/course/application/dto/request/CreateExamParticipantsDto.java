package com.classvar.course.application.dto.request;

import com.classvar.course.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExamParticipantsDto {
  @NotEmpty(message = "name 필드는 비어 있을 수 없습니다.")
  private String name;

  @NotEmpty(message = "department 필드는 비어 있을 수 없습니다.")
  private String department;

  @NotEmpty(message = "participantsId 필드는 비어 있을 수 없습니다.")
  private String participantsId;

  @NotEmpty(message = "email 필드는 비어 있을 수 없습니다.")
  private String email;

  private UserRole role;
}
