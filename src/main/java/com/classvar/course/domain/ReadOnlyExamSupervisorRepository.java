package com.classvar.course.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReadOnlyExamSupervisorRepository {
  Optional<ExamSupervisor> findByExamEntranceUUID(UUID uuid);

  List<ExamSupervisor> findAllExamSupervisorsByCourseId(Long courseId);
}
