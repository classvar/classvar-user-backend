package com.classvar.exam.domain;

import java.util.List;
import java.util.Optional;

public interface ExamRepository {
  Exam save(Exam toSave);

  void delete(Exam toRemove);

  List<Exam> findAll();

  List<Exam> findExamByCourseId(Long courseId);

  Optional<Exam> findExamWithCourseIdAndExamId(Long courseId, Long examId);
}
