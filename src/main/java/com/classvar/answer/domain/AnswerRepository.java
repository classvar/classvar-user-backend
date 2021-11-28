package com.classvar.answer.domain;

import java.util.Optional;

public interface AnswerRepository {

  Answer save(Answer toSave);

  // List<Answer> findStudentAnswerWithExamId(Long examId);

  Optional<Answer> findAnswerWithExamIdAndStudentId(Long examId, Long studentId);
}
