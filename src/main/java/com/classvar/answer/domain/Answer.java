package com.classvar.answer.domain;

import com.classvar.exam.domain.GradingStatus;
import com.classvar.exam.domain.Question;
import com.classvar.student.domain.Student;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long examId;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AnswerSheet> answerSheets = new ArrayList<>();

  private GradingStatus gradingStatus;

  private Integer totalScore;

  private LocalDateTime submitTime;

  protected Answer() {}

  public Answer(Student student, Long examId) {
    this.student = student;
    this.examId = examId;
    this.gradingStatus = GradingStatus.NOT_YET;
    this.totalScore = 0;
  }

  public void recordSubmitTime() {
    this.submitTime = LocalDateTime.now();
  }

  // 기존의 answerSheet을 삭제한 후 새로 넣는다.
  public void addOrUpdateAnswerSheet(Question question, AnswerSheet updatePayload) {
    this.answerSheets.removeIf(answerSheet -> answerSheet.getQuestion().equals(question));

    updatePayload.setAnswer(this);
    updatePayload.setQuestion(question);

    this.answerSheets.add(updatePayload);
  }

  public void addPointToTotalScore(Integer point) {
    this.totalScore += point;
  }

  @Override
  public boolean equals(Object another) {
    if (another == null) return false;
    if (!(another instanceof Answer)) return false;
    if (this == another) return true;

    return this.id == ((Answer) another).id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
