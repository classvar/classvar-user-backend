package com.classvar.answer.application.common;

import com.classvar.answer.domain.Answer;
import com.classvar.answer.domain.AnswerSheet;
import com.classvar.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

  Answer toAnswer(Student student, Long examId);

  @Mapping(target = "submitAnswer", source = "answer")
  AnswerSheet toAnswerSheet(String answer);

  // GetCourseDto toCourseDto(Course course);
}
