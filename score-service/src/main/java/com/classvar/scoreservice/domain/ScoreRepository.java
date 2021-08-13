package com.classvar.scoreservice.domain;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository {
  Score save(Score toSave);

  Optional<Score> findById(Integer id);

  List<Score> findByExamId(Integer examId);

  List<Score> findByExamIdOrderByTotalScoreDesc(Integer examId);

  void removeAllByExamId(Integer examId);
}
