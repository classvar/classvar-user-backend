package com.classvar.exam.application.common;

import com.classvar.exam.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.exam.application.dto.request.CreateOrUpdateQuestionDto;
import com.classvar.exam.application.dto.response.GetExamDetailDto;
import com.classvar.exam.application.dto.response.GetExamInfoDto;
import com.classvar.exam.application.dto.response.GetQuestionDto;
import com.classvar.exam.domain.Exam;
import com.classvar.exam.domain.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamMapper {

  @Mapping(target = "numberOfProblem", ignore = true)
  Exam toExam(CreateOrUpdateExamDto dto);

  GetExamDetailDto toExamDetailDto(Exam exam);

  default GetExamInfoDto toExamInfoDto(Exam exam) {
    return new GetExamInfoDto(exam.getId(), exam.getName());
  }

  Question toQuestion(CreateOrUpdateQuestionDto dto);

  GetQuestionDto toQuestionDto(Question question);
}
