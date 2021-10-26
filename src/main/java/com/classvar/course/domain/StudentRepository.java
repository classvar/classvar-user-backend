package com.classvar.course.domain;

import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findStudentWithCourseIdAndStudentId(Long courseId, Long studentId);
}
