package com.classvar.exam.application;

import com.classvar.exam.application.common.ExamMapper;
import com.classvar.exam.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.exam.domain.Exam;
import com.classvar.exam.domain.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamCommandExecutor {

  private final ExamRepository examRepository;
  private final ExamMapper examMapper;

  @Transactional
  public long createExam(long courseId, CreateOrUpdateExamDto dto) {
    Exam exam = examMapper.toExam(courseId ,dto);

    examRepository.save(exam);
    return exam.getId();
  }

  @Transactional
  public void updateExam(long courseId, long examId, CreateOrUpdateExamDto dto) {
    Exam exam =
        examRepository.findExamWithCourseIdAndExamId(courseId, examId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));

    exam.updateExamInfo(dto.getName(), dto.getExamDate(), dto.getStartTime(), dto.getEndTime());
  }

  @Transactional
  public void deleteExam(long courseId, long examId) {
    Exam exam =
            examRepository.findExamWithCourseIdAndExamId(courseId, examId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));
    examRepository.delete(exam);
  }
}