package com.classvar.course.domain;

import java.util.Optional;

public interface ExamRepository {

  Optional<Exam> findExamWithCourseIdAndExamId(Long courseId, Long examId);
}
