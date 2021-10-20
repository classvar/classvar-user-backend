package com.classvar.scoreservice.application;

import com.classvar.scoreservice.application.common.EntityMapper;
import com.classvar.scoreservice.application.dto.request.SubmittedAnswerDto;
import com.classvar.scoreservice.application.dto.request.UpdateScoreSubmittedAnswerDto;
import com.classvar.scoreservice.domain.Answer;
import com.classvar.scoreservice.domain.AnswerRepository;
import com.classvar.scoreservice.domain.TempUser;
import com.classvar.scoreservice.utils.ErrorCode;
import com.classvar.scoreservice.utils.ScoreException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreCommandExecutor {

  private AnswerRepository answerRepository;
  private EntityMapper entityMapper;

  public ScoreCommandExecutor(AnswerRepository answerRepository, EntityMapper entityMapper) {
    this.answerRepository = answerRepository;
    this.entityMapper = entityMapper;
  }

  @Transactional
  public void submitAnswer(SubmittedAnswerDto dto) {
    // 시험 제출은 무조건 한번이라고 가정

    answerRepository.save(entityMapper.toAnswer(dto));
  }

  @Transactional
  public boolean updateSubmittedAnswerPoint(
      int courseId, int examId, int tempUserId, UpdateScoreSubmittedAnswerDto dto) {
    Answer answer =
        answerRepository
            .findByCourseIdAndExamIdAndTempUserId(courseId, examId, tempUserId)
            .orElseThrow(
                () -> new ScoreException("채점 데이터가 존재하지 않습니다.", ErrorCode.NO_SUCH_SCORE_ID));
    answer.updateScoreInSubmittedAnswer(dto.getQuestionId(), dto.getPoint());

    answerRepository.save(answer);
    return true;
  }

  @Transactional
  public boolean deleteScore(int examId) {
    answerRepository.removeAllByExamId(examId);
    // Course-Service에 getTempUserList를 요청한다.
    TempUser tempUser = new TempUser(1, "김현식", "", "devconf5296@gmail.com");
    return true;
  }
}
