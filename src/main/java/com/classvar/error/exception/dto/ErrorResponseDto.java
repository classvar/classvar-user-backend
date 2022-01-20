package com.classvar.error.exception.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {

  private String code;
  private String message;
  private Integer status;
  private LocalDateTime timestamp;
  private List<InvalidParameterDto> invalidParameters;

}
