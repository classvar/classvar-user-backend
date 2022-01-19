package com.classvar.course.domain;

import java.util.List;
import java.util.Optional;

public interface ExamTakerRepository {
  Optional<ExamTaker> findByUuid(String uuid);

  List<ExamTaker> findAllExamTakersByCourseId(Long courseId);
}
