package com.classvar.course.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteExamSupervisorDto {
  private List<Long> examSupervisorIds;
}
