package com.classvar.course.infra;

import com.classvar.course.domain.Student;
import com.classvar.course.domain.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Long>, StudentRepository {
  @Query("select s from Student s join fetch Course c on c.id =:courseId where s.id =:studentId")
  Optional<Student> findStudentWithCourseIdAndStudentId(
      @Param("courseId") Long courseId, @Param("studentId") Long studentId);
}
