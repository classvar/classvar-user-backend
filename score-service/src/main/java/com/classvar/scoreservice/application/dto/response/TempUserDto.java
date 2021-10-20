package com.classvar.scoreservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TempUserDto {
  private int userId;
  private String name;
  private String department;
  private String email;
}
