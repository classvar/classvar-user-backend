package com.classvar.course.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CourseRepository {

  Course save(Course toSave);

  void delete(Course toRemove);

  List<Course> findAll();

  Optional<Course> findCourseById(Long courseId);

  Optional<Course> findCourseWithExamsById(Long courseId);

  Set<Exam> findAllExamWithCourseId(Long courseId);

  Set<Student> findAllStudentWithCourseId(Long courseId);
}
