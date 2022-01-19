package com.classvar.course.application.common;

import com.classvar.course.application.dto.request.CreateExamTakerDto;
import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.application.dto.response.GetExamTakerDto;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.ExamTaker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  Course toCourse(CreateOrUpdateCourseDto dto);

  GetCourseDto toCourseDto(Course course);

  GetExamTakerDto toExamTakerInfoDto(ExamTaker examTaker);

  ExamTaker toExamTaker(CreateExamTakerDto dto);
}
