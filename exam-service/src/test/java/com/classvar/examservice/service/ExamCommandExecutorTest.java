package com.classvar.examservice.service;

import com.classvar.examservice.application.ExamCommandExecutor;
import com.classvar.examservice.application.dto.request.CreateOrUpdateAnnouncementDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateQuestionDto;
import com.classvar.examservice.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExamCommandExecutorTest {

  @Autowired ExamCommandExecutor examCommandExecutor;

  @Autowired ExamRepository examRepository;

  final CreateOrUpdateAnnouncementDto createOrUpdateAnnouncementDto =
      new CreateOrUpdateAnnouncementDto(null, "제목2", "공지 내용");
  final CreateOrUpdateQuestionDto createOrUpdateQuestionDto =
      new CreateOrUpdateQuestionDto(null, "문제2", QuestionType.CHOICE, null, 10);
  final CreateOrUpdateExamDto createOrUpdateExamDto =
      new CreateOrUpdateExamDto(null, "시험2", LocalDateTime.now(), LocalDateTime.now());

  static final LocalDateTime localDateTime = LocalDateTime.now();

  int setupExamId, setupQuestionId, setupAnnouncementId;

  @Transactional
  @BeforeEach
  public void setup() {
    Exam exam = new Exam("시험1", localDateTime, localDateTime);
    int examId = examRepository.save(exam).getId();
    Exam toTest = examRepository.findById(examId).get();

    List<QuestionChoice> questionChoiceList = new ArrayList<>();
    questionChoiceList.add(new QuestionChoice("선택1"));
    questionChoiceList.add(new QuestionChoice("선택2"));

    Question question = new Question(null, "문제1", QuestionType.CHOICE, questionChoiceList, 10);
    toTest.getQuestions().add(question);
    Announcement announcement = new Announcement(null, "제목1", "공지 내용");
    toTest.getAnnouncements().add(announcement);

    examRepository.save(toTest);

    Exam setupExam = examRepository.findById(examId).get();

    this.setupExamId = setupExam.getId();
    this.setupQuestionId = setupExam.getQuestions().stream().findFirst().get().getId();
    this.setupAnnouncementId = setupExam.getAnnouncements().stream().findFirst().get().getId();
  }

  @AfterEach
  public void cleanSetup() {
    if (examRepository.findById(setupExamId).isPresent()) {
      examRepository.deleteAll();
    }
  }

  @Transactional
  @Test
  public void createEmptyExamTest() {
    int examId = examCommandExecutor.createEmptyExam(createOrUpdateExamDto);

    Exam findExam = examRepository.findById(examId).get();

    assertThat(findExam.getId()).isNotEqualTo(this.setupExamId);
    assertThat(findExam.getName()).isNotEqualTo("시험1").isEqualTo("시험2");
    assertThat(findExam.getStartTime()).isNotEqualTo(localDateTime);
    assertThat(findExam.getEndTime()).isNotEqualTo(localDateTime);
  }

  @Transactional
  @Test
  public void deleteExamIfNotInProgressTest() {
    examCommandExecutor.deleteExamIfNotInProgress(this.setupExamId);

    assertThat(examRepository.findById(this.setupExamId).isPresent()).isEqualTo(false);
  }

  @Transactional
  @Test
  public void updateExamTest() {
    examCommandExecutor.updateExam(this.setupExamId, createOrUpdateExamDto);

    Exam findExam = examRepository.findById(this.setupExamId).get();

    assertThat(findExam.getId()).isEqualTo(this.setupExamId);
    assertThat(findExam.getName()).isEqualTo("시험2");
    assertThat(findExam.getStartTime()).isNotEqualTo(localDateTime);
    assertThat(findExam.getEndTime()).isNotEqualTo(localDateTime);
  }

  @Transactional
  @Test
  public void createQuestionToExamTest() {
    examCommandExecutor.createQuestionToExam(this.setupExamId, createOrUpdateQuestionDto);

    Exam findExam = examRepository.findById(this.setupExamId).get();

    assertThat(findExam.getQuestions().stream().collect(Collectors.toList()).get(1).getName())
        .isEqualTo("문제2");
    assertThat(findExam.getQuestions().size()).isEqualTo(2);
  }

  @Transactional
  @Test
  public void deleteQuestionFromExamTest() {
    examCommandExecutor.deleteQuestionFromExam(this.setupExamId, this.setupQuestionId);

    assertThat(examRepository.findById(this.setupExamId).get().getQuestions().size()).isEqualTo(0);
  }

  @Transactional
  @Test
  public void updateQuestionToExamTest() {
    examCommandExecutor.updateQuestionToExam(
        setupExamId, setupQuestionId, createOrUpdateQuestionDto);

    Exam findExam = examRepository.findById(setupExamId).get();
    assertThat(findExam.getQuestions().stream().collect(Collectors.toList()).get(0).getName())
        .isEqualTo("문제2");
    assertThat(findExam.getQuestions().size()).isEqualTo(1);
  }

  @Transactional
  @Test
  public void createAnnouncementToExamTest() {
    examCommandExecutor.createAnnouncementToExam(setupExamId, createOrUpdateAnnouncementDto);

    Exam findExam = examRepository.findById(this.setupExamId).get();

    assertThat(findExam.getAnnouncements().stream().collect(Collectors.toList()).get(1).getTitle())
        .isEqualTo("제목2");
    assertThat(findExam.getAnnouncements().size()).isEqualTo(2);
  }

  @Transactional
  @Test
  public void deleteAnnouncementFromExamTest() {
    examCommandExecutor.deleteAnnouncementFromExam(this.setupExamId, this.setupAnnouncementId);

    assertThat(examRepository.findById(this.setupExamId).get().getAnnouncements().size())
        .isEqualTo(0);
  }

  @Transactional
  @Test
  public void updateAnnouncementToExam() {
    examCommandExecutor.updateAnnouncementToExam(
        this.setupExamId, this.setupAnnouncementId, createOrUpdateAnnouncementDto);

    Exam findExam = examRepository.findById(this.setupExamId).get();

    assertThat(findExam.getAnnouncements().stream().collect(Collectors.toList()).get(0).getTitle())
        .isEqualTo("제목2");
    assertThat(findExam.getAnnouncements().size()).isEqualTo(1);
  }
}
