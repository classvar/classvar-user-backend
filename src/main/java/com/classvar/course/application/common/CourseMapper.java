package com.classvar.course.application.common;

import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.course.application.dto.request.CreateStudentsDto;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.application.dto.response.GetExamDetailDto;
import com.classvar.course.application.dto.response.GetExamInfoDto;
import com.classvar.course.application.dto.response.GetStudentDto;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.Exam;
import com.classvar.course.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  Course toCourse(CreateOrUpdateCourseDto dto);

  GetCourseDto toCourseDto(Course course);

  @Mapping(target = "numberOfProblem", ignore = true)
  Exam toExam(CreateOrUpdateExamDto dto);

  GetExamDetailDto toExamDetailDto(Exam exam);

  default GetExamInfoDto toExamInfoDto(Exam exam) {
    return new GetExamInfoDto(exam.getId(), exam.getName());
  }

  GetStudentDto toStudentInfoDto(Student student);

  default List<Student> toStudents(CreateStudentsDto dto) {
    return dto.getEmails().stream().map(Student::new).collect(Collectors.toList());
  }
}
