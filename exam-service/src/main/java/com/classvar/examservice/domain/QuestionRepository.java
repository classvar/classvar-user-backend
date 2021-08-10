package com.classvar.examservice.domain;

import java.util.Optional;

public interface QuestionRepository {
  Optional<Question> findById(Integer id);
}
