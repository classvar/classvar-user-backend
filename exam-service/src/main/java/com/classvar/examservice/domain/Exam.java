package com.classvar.examservice.domain;

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

  public boolean removeQuestion(int questionId) {
    return this.questions.removeIf(q -> q.getId() == questionId);
  }

  public boolean updateQuestion(int questionId, Question question) {
    if (!this.questions.removeIf(q -> q.getId() == questionId)) return false;
    this.questions.add(question);
    return true;
  }

  public void addAnnouncement(Announcement announcement) {
    this.announcements.add(announcement);
  }

  public boolean removeAnnouncement(int announcementId) {
    return this.getAnnouncements().removeIf(a -> a.getId() == announcementId);
  }

  public boolean updateAnnouncement(int announcementId, Announcement announcement) {
    if (!this.announcements.removeIf(a -> a.getId() == announcementId)) return false;
    this.announcements.add(announcement);
    return true;
  }
}
