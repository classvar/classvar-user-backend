package com.classvar.student.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
  Student save(Student student);

  void delete(Student student);

  void deleteByIdIn(List<Long> studentIds);

  Optional<Student> findByUuid(String uuid);

  List<Student> findStudentByCourseId(Long courseId);

  List<Student> findStudentByIdIn(List<Long> studentIds);
}
