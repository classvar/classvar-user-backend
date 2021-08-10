package com.classvar.examservice.service;

import com.classvar.examservice.application.ExamQueryProcessor;
import com.classvar.examservice.application.dto.response.GetAnnouncementDto;
import com.classvar.examservice.application.dto.response.GetExamDto;
import com.classvar.examservice.application.dto.response.GetQuestionDto;
import com.classvar.examservice.domain.*;
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
public class ExamQueryProcessorTest {

  @Autowired ExamQueryProcessor examQueryProcessor;

  @Autowired ExamRepository examRepository;

  public static final LocalDateTime localDateTime = LocalDateTime.now();

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
    toTest.addQuestion(question);
    Announcement announcement = new Announcement(null, "제목1", "공지 내용");
    toTest.addAnnouncement(announcement);

    examRepository.save(toTest);

    Exam setupExam = examRepository.findById(examId).get();

    this.setupExamId = setupExam.getId();
    this.setupQuestionId =
        setupExam.getQuestions().stream().collect(Collectors.toList()).get(0).getId();
    this.setupAnnouncementId =
        setupExam.getAnnouncements().stream().collect(Collectors.toList()).get(0).getId();
  }

  @Transactional
  @Test
  public void getExamMetadataWithIdTest() {
    GetExamDto toTest = examQueryProcessor.getExamMetadataWithId(this.setupExamId);
    assertThat(toTest.getName()).isEqualTo("시험1");
    assertThat(toTest.getBeginAt()).isEqualTo(localDateTime);
    assertThat(toTest.getEndAt()).isEqualTo(localDateTime);
  }

  @Transactional
  @Test
  public void getExamWithQuestionAndAnnouncementTest() {
    GetExamDto examDto = examQueryProcessor.getExamWithQuestionAndAnnouncement(this.setupExamId);
    assertThat(examDto.getName()).isEqualTo("시험1");
    assertThat(examDto.getBeginAt()).isEqualTo(localDateTime);
    assertThat(examDto.getEndAt()).isEqualTo(localDateTime);
    assertThat(examDto.getQuestionAmount()).isEqualTo(examDto.getQuestions().size());
    assertThat(examDto.getQuestions().stream().findFirst().get().getName()).isEqualTo("문제1");
    assertThat(examDto.getQuestions().stream().findFirst().get().getType())
        .isEqualTo(QuestionType.CHOICE);
    assertThat(examDto.getQuestions().get(0).getDescription().get(0).getContent()).isEqualTo("선택1");
    assertThat(examDto.getQuestions().get(0).getDescription().get(1).getContent()).isEqualTo("선택2");
    assertThat(examDto.getQuestions().stream().findFirst().get().getPoint()).isEqualTo(10);
    assertThat(examDto.getAnnouncements().stream().findFirst().get().getTitle()).isEqualTo("제목1");
    assertThat(examDto.getAnnouncements().stream().findFirst().get().getContent())
        .isEqualTo("공지 내용");
  }

  @Transactional
  @Test
  public void getQuestionFromExamWithIdTest() {
    GetQuestionDto questionDto =
        examQueryProcessor.getQuestionFromExamWithId(this.setupExamId, this.setupQuestionId);

    assertThat(questionDto.getName()).isEqualTo("문제1");
    assertThat(questionDto.getType()).isEqualTo(QuestionType.CHOICE);
    assertThat(questionDto.getDescription().get(0).getContent()).isEqualTo("선택1");
    assertThat(questionDto.getDescription().get(1).getContent()).isEqualTo("선택2");
    assertThat(questionDto.getPoint()).isEqualTo(10);
  }

  @Transactional
  @Test
  public void getAnnouncementFromExamWithIdTest() {
    GetAnnouncementDto announcementDto =
        examQueryProcessor.getAnnouncementFromExamWithId(
            this.setupExamId, this.setupAnnouncementId);

    assertThat(announcementDto.getTitle()).isEqualTo("제목1");
    assertThat(announcementDto.getContent()).isEqualTo("공지 내용");
  }
}
