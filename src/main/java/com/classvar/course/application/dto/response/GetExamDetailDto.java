package com.classvar.course.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetExamDetailDto {

  private String name;

  private String examDate;

  private String startTime;

  private String endTime;
}
