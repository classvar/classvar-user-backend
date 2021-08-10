package com.classvar.examservice.application;

import com.classvar.examservice.application.common.EntityMapper;
import com.classvar.examservice.application.dto.request.CreateOrUpdateAnnouncementDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateQuestionDto;
import com.classvar.examservice.controller.common.ErrorCode;
import com.classvar.examservice.controller.common.ExamException;
import com.classvar.examservice.domain.Announcement;
import com.classvar.examservice.domain.Exam;
import com.classvar.examservice.domain.ExamRepository;
import com.classvar.examservice.domain.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamCommandExecutor {

  private ExamRepository examRepository;

  private EntityMapper entityMapper;

  // 기본이 생성자 주입
  public ExamCommandExecutor(ExamRepository examRepository, EntityMapper entityMapper) {
    this.examRepository = examRepository;
    this.entityMapper = entityMapper;
  }

  // withNameAndDates
  // Initial
  // 둘 다 별로인 네이밍
  @Transactional
  public int createEmptyExam(CreateOrUpdateExamDto dto) {
    Exam toCreate = entityMapper.toExam(dto);
    examRepository.save(toCreate);
    return toCreate.getId();
  }

  /*
  returns false if exam is in progress.
   */
  @Transactional
  public boolean deleteExamIfNotInProgress(int examId) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));
    if (exam.isExamInProgress()) return false;
    examRepository.delete(exam);
    return true;
  }

  // 문제: update=set=change 똑같다.
  // 무엇을 왜 업데이트하느냐가 담겨야, 도메인 개념이 있는거죠.
  // update 행위인데 why update 가 네이밍으로 담겨야.
  @Transactional
  public boolean updateExam(int examId, CreateOrUpdateExamDto dto) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));
    if (exam.isExamInProgress()) {
      throw new ExamException("시험중에는 문제를 삭제할 수 없습니다.", ErrorCode.DELETE_QUESTION_DENIED);
    }
    exam.changeName(dto.getName());
    exam.changeSchedule(dto.getBeginAt(), dto.getEndAt());
    return true;
  }

  @Transactional
  public void createQuestionToExam(int examId, CreateOrUpdateQuestionDto dto) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));

    Question toCreate = entityMapper.toQuestion(dto);
    exam.addQuestion(toCreate);
  }

  @Transactional
  public boolean deleteQuestionFromExam(int examId, int questionId) {

    // 좀 더 나은 방식이 있을듯..?
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));
    if (exam.isExamInProgress()) {
      throw new ExamException("시험중에는 문제를 삭제할 수 없습니다.", ErrorCode.DELETE_QUESTION_DENIED);
    }
    exam.removeQuestion(questionId);
    return true;
  }

  @Transactional
  public boolean updateQuestionToExam(int examId, int questionId, CreateOrUpdateQuestionDto dto) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));

    Question toUpdate = entityMapper.toQuestion(dto);
    exam.updateQuestion(questionId, toUpdate);

    examRepository.save(exam);
    return true;
  }

  @Transactional
  public void createAnnouncementToExam(int examId, CreateOrUpdateAnnouncementDto dto) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));

    Announcement toCreate = entityMapper.toAnnouncement(dto);

    exam.addAnnouncement(toCreate);
  }

  @Transactional
  public boolean deleteAnnouncementFromExam(int examId, int announcementId) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));

    exam.removeAnnouncement(announcementId);
    return true;
  }

  @Transactional
  public boolean updateAnnouncementToExam(
      int examId, int announcementId, CreateOrUpdateAnnouncementDto dto) {
    Exam exam =
        examRepository
            .findById(examId)
            .orElseThrow(() -> new ExamException("존재하지 않는 시험입니다.", ErrorCode.NO_SUCH_EXAM_ID));

    Announcement toUpdate = entityMapper.toAnnouncement(dto);
    exam.updateAnnouncement(announcementId, toUpdate);

    examRepository.save(exam);
    return true;
  }
}
