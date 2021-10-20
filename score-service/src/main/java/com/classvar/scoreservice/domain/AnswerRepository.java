package com.classvar.scoreservice.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AnswerRepository {
  Answer save(Answer toSave);

  Optional<Answer> findByCourseIdAndExamIdAndTempUserId(
      Integer courseId, Integer examId, Integer tempUserId);

  List<Answer> findAllAnswerWithExamId(Integer examId);

  Set<SubmittedAnswer> findSubmittedAnswerWithExamIdAndTempUserId(
      Integer examId, Integer tempUserId);

  Optional<SubmittedAnswer> findSubmittedAnswerWithExamIdAndTempUserIdAndQuestionId(Integer examId);

  void removeAllByExamId(Integer examId);
}
