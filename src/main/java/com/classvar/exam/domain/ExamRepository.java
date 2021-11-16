package com.classvar.exam.domain;

import java.util.List;
import java.util.Optional;

public interface ExamRepository {
  Exam save(Exam toSave);

  void delete(Exam toRemove);

  Optional<Exam> findExamById(Long examId);

  Optional<Exam> findExamByIdAndCourseId(Long examId, Long courseId);

  List<Exam> findExamByCourseId(Long courseId);
}
