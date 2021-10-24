package com.classvar.course.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCourseListDto {
  private List<GetCourseDto> courses = new ArrayList<>();
}
