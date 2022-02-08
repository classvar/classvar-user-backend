package com.classvar.course.domain;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

  Course save(Course toSave);

  void delete(Course toRemove);

  List<Course> findAll();

  Optional<Course> findCourseById(Long courseId);
}
