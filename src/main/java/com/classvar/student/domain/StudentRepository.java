package com.classvar.student.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
  Student save(Student student);

  void delete(Student student);

  Optional<Student> findByUuid(String uuid);

  List<Student> findAllStudentWithCourseId(Long courseId);

  List<Student> findStudentWithIds(List<Long> studentIds);
}
