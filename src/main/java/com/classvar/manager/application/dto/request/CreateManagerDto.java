package com.classvar.manager.application.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateManagerDto {

  @Email(message = "형식에 맞지 않는 이메일입니다.")
  @NotEmpty(message = "이메일은 반드시 넣어야 합니다.")
  private String email;
  @NotEmpty(message = "학번은 반드시 넣어야 합니다.")
  private String managerId;
}
