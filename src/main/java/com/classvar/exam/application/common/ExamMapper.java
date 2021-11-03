package com.classvar.exam.application.common;

import com.classvar.exam.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.exam.application.dto.response.GetExamDetailDto;
import com.classvar.exam.application.dto.response.GetExamInfoDto;
import com.classvar.exam.domain.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamMapper {


  @Mapping(target = "numberOfProblem", ignore = true)
  Exam toExam(Long courseId,CreateOrUpdateExamDto dto);

  GetExamDetailDto toExamDetailDto(Exam exam);

  default GetExamInfoDto toExamInfoDto(Exam exam) {
    return new GetExamInfoDto(exam.getId(), exam.getName());
  }
}