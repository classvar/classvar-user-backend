package com.classvar.examservice.infra;

import com.classvar.examservice.domain.Question;
import com.classvar.examservice.domain.QuestionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQuestionRepository
    extends JpaRepository<Question, Integer>, QuestionRepository {}
