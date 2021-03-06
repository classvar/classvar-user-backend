package com.classvar.exam.application;

import com.classvar.exam.application.common.ExamMapper;
import com.classvar.exam.application.dto.response.GetExamDetailDto;
import com.classvar.exam.application.dto.response.GetExamInfoDto;
import com.classvar.exam.application.dto.response.GetQuestionDto;
import com.classvar.exam.domain.Exam;
import com.classvar.exam.domain.ExamRepository;
import com.classvar.exam.domain.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamQueryProcessor {

  private final ExamRepository examRepository;
  private final QuestionRepository questionRepository;
  private final ExamMapper examMapper;

  public GetExamDetailDto getExamDetailWithId(long courseId, long examId) {
    Exam exam =
        examRepository
            .findExamByIdAndCourseId(examId, courseId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));

    return examMapper.toExamDetailDto(exam);
  }

  public List<GetExamInfoDto> getExamList(long courseId) {
    return examRepository.findExamByCourseId(courseId).stream()
        .map(examMapper::toExamInfoDto)
        .collect(Collectors.toList());
  }

  public List<GetQuestionDto> getQuestionList(long courseId, long examId) {
    return questionRepository.findQuestionWithCourseIdAndExamId(courseId, examId).stream()
        .map(examMapper::toQuestionDto)
        .collect(Collectors.toList());
  }
}
