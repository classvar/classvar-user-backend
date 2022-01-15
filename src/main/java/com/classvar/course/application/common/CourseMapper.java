package com.classvar.course.application.common;

import com.classvar.course.application.dto.request.CreateExamTakerDto;
import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.request.UpdateExamTakerInfoDto;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.application.dto.response.GetExamTakerDto;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.ExamTaker;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  Course toCourse(CreateOrUpdateCourseDto dto);

  GetCourseDto toCourseDto(Course course);

  GetExamTakerDto toExamTakerInfoDto(ExamTaker examTaker);

  ExamTaker toExamTaker(UpdateExamTakerInfoDto dto);

  default List<ExamTaker> toExamTakers(CreateExamTakerDto dto) {
    List<ExamTaker> examTakers = new ArrayList<>();
    for (String email : dto.getEmails()) {
      examTakers.add(new ExamTaker(null, null, null, email));
    }

    return examTakers;
  }
}
