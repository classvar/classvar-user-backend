package com.classvar.examservice.domain;

import com.classvar.examservice.controller.common.ErrorCode;
import com.classvar.examservice.controller.common.ExamException;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "exam_id")
  private Set<Question> questions = new LinkedHashSet<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "exam_id")
  private Set<Announcement> announcements = new LinkedHashSet<>();

  @Enumerated(EnumType.STRING)
  private ExamProgress state;

  protected Exam() {}

  public Exam(String name, LocalDateTime startTime, LocalDateTime endTime) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.state = ExamProgress.Todo;
  }

  public boolean isExamInProgress() {
    return this.state == ExamProgress.Doing;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void changeSchedule(LocalDateTime startTime, LocalDateTime endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public void addQuestion(Question question) {
    this.questions.add(question);
  }

  public void removeQuestion(int questionId) {
    if (!this.questions.removeIf(q -> q.getId() == questionId)) {
      throw new ExamException("문제가 존재하지 않습니다.", ErrorCode.NO_SUCH_QUESTION_ID);
    }
  }

  public boolean updateQuestion(int questionId, Question question) {
    if (!this.questions.removeIf(q -> q.getId() == questionId)) {
      throw new ExamException("문제가 존재하지 않습니다.", ErrorCode.NO_SUCH_QUESTION_ID);
    }
    this.questions.add(question);
    return true;
  }

  public void addAnnouncement(Announcement announcement) {
    this.announcements.add(announcement);
  }

  public void removeAnnouncement(int announcementId) {
    if (!this.getAnnouncements().removeIf(a -> a.getId() == announcementId)) {
      throw new ExamException("공지가 존재하지 않습니다.", ErrorCode.NO_SUCH_ANNOUNCEMENT_ID);
    }
  }

  public boolean updateAnnouncement(int announcementId, Announcement announcement) {
    if (!this.announcements.removeIf(a -> a.getId() == announcementId)) {
      throw new ExamException("공지가 존재하지 않습니다.", ErrorCode.NO_SUCH_ANNOUNCEMENT_ID);
    }
    this.announcements.add(announcement);
    return true;
  }
}
