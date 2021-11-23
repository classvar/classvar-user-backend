package com.classvar.admin.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAdminDto {

  @NotEmpty(message = "email 필드는 비어 있을 수 없습니다.")
  private String email;

  @NotEmpty
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$")
  private String password;

  @NotEmpty(message = "이름은 비어 있을 수 없습니다.")
  private String name;

  @NotEmpty(message = "department 필드는 비어 있을 수 없습니다.")
  private String department;
}
