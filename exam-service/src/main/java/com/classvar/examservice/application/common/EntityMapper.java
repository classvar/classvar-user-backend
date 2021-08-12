package com.classvar.examservice.application.common;

import com.classvar.examservice.application.dto.request.CreateOrUpdateAnnouncementDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateQuestionDto;
import com.classvar.examservice.application.dto.response.GetAnnouncementDto;
import com.classvar.examservice.application.dto.response.GetExamDto;
import com.classvar.examservice.application.dto.response.GetQuestionDto;
import com.classvar.examservice.domain.Announcement;
import com.classvar.examservice.domain.Exam;
import com.classvar.examservice.domain.Question;
import com.classvar.examservice.domain.QuestionChoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  @Mapping(source = "beginAt", target = "startTime")
  @Mapping(source = "endAt", target = "endTime")
  @Mapping(target = "questions", ignore = true)
  @Mapping(target = "announcements", ignore = true)
  @Mapping(target = "state", ignore = true)
  Exam toExam(CreateOrUpdateExamDto dto);

  @Mapping(source = "startTime", target = "beginAt")
  @Mapping(source = "endTime", target = "endAt")
  @Mapping(target = "questionAmount", expression = "java(exam.getQuestions().size())")
  GetExamDto toExamDto(Exam exam);

  default Question toQuestion(CreateOrUpdateQuestionDto dto) {
    Question question =
        new Question(
            dto.getId(),
            dto.getName(),
            dto.getType(),
            dto.getDescription() == null
                ? null
                : dto.getDescription().stream()
                    .map(QuestionChoice::new)
                    .collect(Collectors.toList()),
            dto.getPoint());
    return question;
  }

  default GetQuestionDto toQuestionDto(Question question) {
    GetQuestionDto questionDto =
        new GetQuestionDto(
            question.getId(),
            question.getName(),
            question.getType().toString(),
            question.getDescription() == null
                ? null
                : question.getDescription().stream()
                    .map(QuestionChoice::getContent)
                    .collect(Collectors.toList()),
            question.getPoint());
    return questionDto;
  }

  Announcement toAnnouncement(CreateOrUpdateAnnouncementDto dto);

  @Mapping(source = "id", target = "announceId")
  GetAnnouncementDto toAnnouncementDto(Announcement announcement);
}
