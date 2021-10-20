package com.classvar.scoreservice.domain;

import java.util.Optional;

public interface SubmittedAnswerRepository {
  Optional<SubmittedAnswer> findByQuestionId(Integer id);
}
