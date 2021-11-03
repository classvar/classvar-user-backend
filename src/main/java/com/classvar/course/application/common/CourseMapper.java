package com.classvar.course.application.common;

import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.domain.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  Course toCourse(CreateOrUpdateCourseDto dto);

  GetCourseDto toCourseDto(Course course);
}
