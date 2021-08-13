package com.classvar.scoreservice.application;

import com.classvar.scoreservice.application.dto.ScoreDto;
import com.classvar.scoreservice.domain.AnswerSheet;
import com.classvar.scoreservice.domain.Score;
import com.classvar.scoreservice.domain.ScoreRepository;
import com.classvar.scoreservice.utils.EntityMapper;
import com.classvar.scoreservice.utils.ErrorCode;
import com.classvar.scoreservice.utils.ScoreException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreCommandExecutor {

  private ScoreRepository scoreRepository;
  private EntityMapper entityMapper;

  public ScoreCommandExecutor(ScoreRepository scoreRepository, EntityMapper entityMapper) {
    this.scoreRepository = scoreRepository;
    this.entityMapper = entityMapper;
  }

  @Transactional
  public void creatScore(ScoreDto dto) {
    Score score = entityMapper.toScore(dto);
    for (AnswerSheet answerSheet : score.getAnswers()) {
      score.addAnswerSheet(answerSheet);
    }
    scoreRepository.save(score);
  }

  @Transactional
  public boolean updateScore(int scoreId, ScoreDto dto) {
    Score score =
        scoreRepository
            .findById(scoreId)
            .orElseThrow(
                () -> new ScoreException("채점 데이터가 존재하지 않습니다.", ErrorCode.NO_SUCH_SCORE_ID));

    Score updateScore = entityMapper.toScore(dto);

    if (score.getId().equals(updateScore.getId())) {
      scoreRepository.save(updateScore);
    }
    return true;
  }

  @Transactional
  public boolean deleteScore(int examId) {
    scoreRepository.removeAllByExamId(examId);
    return true;
  }
}
