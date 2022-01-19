package com.classvar.course.domain;

import java.util.List;
import java.util.Optional;

public interface ExamSupervisorRepository {
  Optional<ExamSupervisor> findByUuid(String uuid);

  List<ExamSupervisor> findAllExamSupervisorsByCourseId(Long courseId);
}
