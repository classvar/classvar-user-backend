package com.classvar.scoreservice.infra;

import com.classvar.scoreservice.domain.Answer;
import com.classvar.scoreservice.domain.AnswerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAnswerRepository extends JpaRepository<Answer, Integer>, AnswerRepository {

  @Query("select a from Answer a where a.examId= :examId")
  List<Answer> findAllAnswerWithExamId(@Param("examId") Integer examId);
}
