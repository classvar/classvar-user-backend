package com.classvar.scoreservice.application;

import com.classvar.scoreservice.application.dto.GetScoreWithTotalPointDto;
import com.classvar.scoreservice.application.dto.ScoreDto;
import com.classvar.scoreservice.domain.ScoreRepository;
import com.classvar.scoreservice.utils.EntityMapper;
import com.classvar.scoreservice.utils.ErrorCode;
import com.classvar.scoreservice.utils.ScoreException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreQueryProcessor {

  private ScoreRepository scoreRepository;
  private EntityMapper entityMapper;

  public ScoreQueryProcessor(ScoreRepository scoreRepository, EntityMapper entityMapper) {
    this.scoreRepository = scoreRepository;
    this.entityMapper = entityMapper;
  }

  public ScoreDto getScoreWithId(int scoreId) {
    return entityMapper.toScoreDto(
        scoreRepository
            .findById(scoreId)
            .orElseThrow(
                () -> new ScoreException("채점 데이터가 존재하지 않습니다.", ErrorCode.NO_SUCH_SCORE_ID)));
  }

  public List<ScoreDto> getScoreListWithExamId(int examId) {
    return scoreRepository.findByExamId(examId).stream()
        .map(score -> entityMapper.toScoreDto(score))
        .collect(Collectors.toList());
  }

  public List<GetScoreWithTotalPointDto> getScoreResultWithExamId(int examId) {
    return scoreRepository.findByExamIdOrderByTotalScoreDesc(examId).stream()
        .map(score -> entityMapper.toGetScoreWithTotalPointDto(score))
        .collect(Collectors.toList());
  }

  public List<GetScoreWithTotalPointDto> getScoreResultFileWithExamId(int examId) {
    return scoreRepository.findByExamIdOrderByTotalScoreDesc(examId).stream()
        .map(score -> entityMapper.toGetScoreWithTotalPointDto(score))
        .collect(Collectors.toList());
  }
}
