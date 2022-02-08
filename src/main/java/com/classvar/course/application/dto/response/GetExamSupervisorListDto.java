package com.classvar.course.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetExamSupervisorListDto {

  List<GetExamSupervisorDto> managers;
}
