package com.classvar.manager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetManagerDto {

  private long id;
  private String name;
  private String managerId;
  private String email;
  private Boolean verified;
}
