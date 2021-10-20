package com.classvar.scoreservice.domain;

import com.classvar.scoreservice.utils.ErrorCode;
import com.classvar.scoreservice.utils.ScoreException;
import lombok.Getter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer courseId;
  private Integer examId;

  @Embedded private TempUser tempUser;

  private int totalScore;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "score_id")
  private Set<SubmittedAnswer> submittedAnswers = new LinkedHashSet<>();

  public Answer(
      Integer courseId,
      Integer examId,
      Integer tempUserId,
      Set<SubmittedAnswer> submittedAnswers,
      int totalScore) {
    this.courseId = courseId;
    this.examId = examId;
    this.tempUserId = tempUserId;
    this.submittedAnswers = submittedAnswers;
    this.totalScore = totalScore;
  }

  protected Answer() {}

  public void addSubmittedAnswer(SubmittedAnswer submittedAnswer) {
    this.submittedAnswers.add(submittedAnswer);
  }

  public void updateScoreInSubmittedAnswer(Integer questionId, int point) {
    for (SubmittedAnswer oldSubmittedAnswer : this.submittedAnswers) {
      if (oldSubmittedAnswer.getQuestionId().equals(questionId)) {
        oldSubmittedAnswer.updateChangedPoint(point);
      }
      break;
    }
    throw new ScoreException("답안이 존재하지 않습니다.", ErrorCode.NO_SUCH_ANSWER_SHEET_ID);
  }
}
