package com.classvar.scoreservice.infra;

import com.classvar.scoreservice.domain.SubmittedAnswer;
import com.classvar.scoreservice.domain.SubmittedAnswerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSubmittedAnswerRepository
    extends JpaRepository<SubmittedAnswer, Integer>, SubmittedAnswerRepository {}
