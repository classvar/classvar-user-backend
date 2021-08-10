package com.classvar.examservice.application;

import com.classvar.examservice.application.common.EntityMapper;
import com.classvar.examservice.application.dto.response.GetAnnouncementDto;
import com.classvar.examservice.application.dto.response.GetExamDto;
import com.classvar.examservice.application.dto.response.GetQuestionDto;
import com.classvar.examservice.controller.common.ErrorCode;
import com.classvar.examservice.controller.common.ExamException;
import com.classvar.examservice.domain.AnnouncementRepository;
import com.classvar.examservice.domain.ExamRepository;
import com.classvar.examservice.domain.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamQueryProcessor {

  private ExamRepository examRepository;
  private AnnouncementRepository announcementRepository;
  private QuestionRepository questionRepository;
  private EntityMapper entityMapper;

  public ExamQueryProcessor(
      ExamRepository examRepository,
      EntityMapper entityMapper,
      AnnouncementRepository announcementRepository,
      QuestionRepository questionRepository) {
    this.examRepository = examRepository;
    this.entityMapper = entityMapper;
    this.announcementRepository = announcementRepository;
    this.questionRepository = questionRepository;
  }

  public GetExamDto getExamMetadataWithId(int examId) {
    return entityMapper.toExamDto(
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("시험이 존재하지 않습니다.", ErrorCode.NO_SUCH_EXAM_ID)));
  }

  public GetExamDto getExamWithQuestionAndAnnouncement(int examId) {
    return entityMapper.toExamDto(
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("시험이 존재하지 않습니다.", ErrorCode.NO_SUCH_EXAM_ID)));
  }

  public GetQuestionDto getQuestionFromExamWithId(int examId, int questionId) {
    return entityMapper.toQuestionDto(
        questionRepository
            .findById(questionId)
            .orElseThrow(() -> new ExamException("문제가 존재하지 않습니다.", ErrorCode.NO_SUCH_QUESTION_ID)));
  }

  public GetAnnouncementDto getAnnouncementFromExamWithId(int examId, int announcementId) {
    return entityMapper.toAnnouncementDto(
        announcementRepository
            .findById(announcementId)
            .orElseThrow(
                () -> new ExamException("공지가 존재하지 않습니다.", ErrorCode.NO_SUCH_ANNOUNCEMENT_ID)));
  }

  public List<GetQuestionDto> getQuestionListFromExamWithId(int examId) {
    return examRepository.findAllQuestionsById(examId).stream()
        .map(question -> entityMapper.toQuestionDto(question))
        .collect(Collectors.toList());
  }

  public List<GetAnnouncementDto> getAnnouncementListFromExamWithId(int examId) {
    return examRepository.findAllAnnouncementsById(examId).stream()
        .map(announcement -> entityMapper.toAnnouncementDto(announcement))
        .collect(Collectors.toList());
  }
}
