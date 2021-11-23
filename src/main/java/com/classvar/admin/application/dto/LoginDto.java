package com.classvar.admin.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
  @NotEmpty(message = "email 필드는 비어 있을 수 없습니다")
  private String email;

  @NotEmpty(message = "password 필드는 비어 있을 수 없습니다")
  private String password;
}
