package com.classvar.exam.domain;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

  Optional<Question> findById(Long questionId);

  List<Question> findQuestionWithCourseIdAndExamId(Long courseId, Long examId);
}
