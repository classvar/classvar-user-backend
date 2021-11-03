package com.classvar.exam.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetExamDetailDto {

  private String name;

  private LocalDate examDate;

  private LocalTime startTime;

  private LocalTime endTime;
}
