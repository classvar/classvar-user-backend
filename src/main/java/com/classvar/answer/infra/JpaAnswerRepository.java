package com.classvar.answer.infra;

import com.classvar.answer.domain.Answer;
import com.classvar.answer.domain.AnswerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAnswerRepository extends JpaRepository<Answer, Long>, AnswerRepository {

  //    @Query(
  //        "select sa from AnswerSheet  join fetch Student s on s.id = sa.student.id where
  // sa.exam.id
  //   =:examId")
  //    List<Answer> findStudentAnswerWithExamId(@Param("examId") Long examId);

  @Query(
      "select a from Answer a join fetch Student s on a.student.id = s.id where a.examId =:examId and a.student.id =:studentId")
  Optional<Answer> findAnswerWithExamIdAndStudentId(
      @Param("examId") Long examId, @Param("studentId") Long studentId);
}
