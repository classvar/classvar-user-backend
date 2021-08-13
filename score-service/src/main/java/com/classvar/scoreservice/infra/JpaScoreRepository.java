package com.classvar.scoreservice.infra;

import com.classvar.scoreservice.domain.Score;
import com.classvar.scoreservice.domain.ScoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaScoreRepository extends JpaRepository<Score, Integer>, ScoreRepository {}
