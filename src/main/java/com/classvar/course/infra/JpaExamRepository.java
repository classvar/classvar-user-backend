package com.classvar.course.infra;

import com.classvar.course.domain.Exam;
import com.classvar.course.domain.ExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaExamRepository extends JpaRepository<Exam, Long>, ExamRepository {
  @Query("select e from Exam e join fetch Course c on c.id =:courseId where e.id =:examId")
  Optional<Exam> findExamWithCourseIdAndExamId(
      @Param("courseId") Long courseId, @Param("examId") Long examId);
}
