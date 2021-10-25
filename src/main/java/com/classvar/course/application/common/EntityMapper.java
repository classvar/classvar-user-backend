package com.classvar.course.application.common;

import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.application.dto.response.GetExamDetailDto;
import com.classvar.course.application.dto.response.GetExamInfoDto;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  Course toCourse(CreateOrUpdateCourseDto dto);

  GetCourseDto toCourseDto(Course course);

  @Mapping(target = "numberOfProblem", ignore = true)
  Exam toExam(CreateOrUpdateExamDto dto);

  GetExamDetailDto toExamDetailDto(Exam exam);

  default GetExamInfoDto toExamInfoDto(Exam exam) {
    return new GetExamInfoDto(exam.getId(), exam.getName());
  }
}
