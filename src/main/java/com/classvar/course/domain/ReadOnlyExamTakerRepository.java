package com.classvar.course.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReadOnlyExamTakerRepository {
  Optional<ExamTaker> findByExamEntranceUUID(UUID uuid);

  List<ExamTaker> findAllExamTakersByCourseId(Long courseId);
}
