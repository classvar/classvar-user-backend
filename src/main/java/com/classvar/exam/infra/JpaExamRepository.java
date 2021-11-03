package com.classvar.exam.infra;

import com.classvar.exam.domain.Exam;
import com.classvar.exam.domain.ExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaExamRepository extends JpaRepository<Exam, Long>, ExamRepository {
  @Query("select e from Exam e where e.id =:examId and e.courseId =:courseId")
  Optional<Exam> findExamWithCourseIdAndExamId(
      @Param("courseId") Long courseId, @Param("examId") Long examId);
}
