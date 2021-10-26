package com.classvar.course.infra;

import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.Exam;
import com.classvar.course.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long>, CourseRepository {
 @Query("select c,e from Course c left join Exam e on e.course.id =:courseId")
  Optional<Course> findCourseWithExamsById(@Param("courseId") Long courseId);

  @Query("select c.exams from Course c where c.id =:courseId")
  Set<Exam> findAllExamWithCourseId(@Param("courseId") Long courseId);

  @Query("select c.students from Course c where c.id =:courseId")
  Set<Student> findAllStudentWithCourseId(@Param("courseId") Long courseId);
}
