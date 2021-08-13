package com.classvar.scoreservice.domain;

import com.classvar.scoreservice.utils.ErrorCode;
import com.classvar.scoreservice.utils.ScoreException;
import lombok.Getter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private Integer examId;
  private String name;
  private String email;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "score_id")
  private Set<AnswerSheet> answers = new LinkedHashSet<>();

  private int totalScore;

  public Score(Integer id, Integer examId, String name, String email, Set<AnswerSheet> answers) {
    this.id = id;
    this.examId = examId;
    this.name = name;
    this.email = email;
    this.answers = answers;
    this.totalScore = answers.stream().mapToInt(AnswerSheet::getPoint).sum();
  }

  protected Score() {}

  public void addAnswerSheet(AnswerSheet answerSheet) {
    this.answers.add(answerSheet);
  }

  public void updateAnswerSheet(int answerSheetId, AnswerSheet answerSheet) {
    if (!this.answers.removeIf(a -> a.getId() == answerSheetId)) {
      throw new ScoreException("답안이 존재하지 않습니다.", ErrorCode.NO_SUCH_ANSWER_SHEET_ID);
    }
    this.answers.add(answerSheet);
  }
}
