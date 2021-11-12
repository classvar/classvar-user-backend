package com.classvar.exam.infra;

import com.classvar.exam.domain.Question;
import com.classvar.exam.domain.QuestionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaQuestionRepository extends JpaRepository<Question, Long>, QuestionRepository {

  @Query(
      "select q from Question q left join Exam e on q.exam.id = e.id where e.courseId =:courseId and e.id =:examId")
  List<Question> findQuestionWithCourseIdAndExamId(
      @Param("courseId") Long courseId, @Param("examId") Long examId);
}
