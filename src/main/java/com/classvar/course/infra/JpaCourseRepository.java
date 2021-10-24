package com.classvar.course.infra;

import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long>, CourseRepository {

  @Query("select c.exams from Course c where c.id =:courseId")
  Set<Exam> findAllExamWithCourseId(@Param("courseId") Long courseId);
}
