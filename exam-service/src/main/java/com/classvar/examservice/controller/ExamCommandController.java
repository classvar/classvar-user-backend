package com.classvar.examservice.controller;

import com.classvar.examservice.application.ExamCommandExecutor;
import com.classvar.examservice.application.dto.request.CreateOrUpdateAnnouncementDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.examservice.application.dto.request.CreateOrUpdateQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExamCommandController {

  @Autowired private ExamCommandExecutor examCommandExecutor;

  @PostMapping("/exam")
  public ResponseEntity<Long> createExam(@Valid @RequestBody CreateOrUpdateExamDto dto) {
    long examId = examCommandExecutor.createEmptyExam(dto);
    return new ResponseEntity<>(examId, HttpStatus.OK);
  }

  @PostMapping("/exam/{examId}/questions")
  public ResponseEntity createQuestion(
      @PathVariable int examId, @Valid @RequestBody CreateOrUpdateQuestionDto dto) {
    examCommandExecutor.createQuestionToExam(examId, dto);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/exam/{examId}/announcements")
  public ResponseEntity createAnnouncement(
      @PathVariable int examId, @Valid @RequestBody CreateOrUpdateAnnouncementDto dto) {
    examCommandExecutor.createAnnouncementToExam(examId, dto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping("/exam/{examId}")
  public ResponseEntity<Boolean> updateExam(
      @PathVariable int examId, @Valid @RequestBody CreateOrUpdateExamDto dto) {
    boolean updated = examCommandExecutor.updateExam(examId, dto);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

  @PutMapping("/exam/{examId}/questions/{questionId}")
  public ResponseEntity<Boolean> updateQuestion(
      @PathVariable int examId,
      @PathVariable int questionId,
      @Valid @RequestBody CreateOrUpdateQuestionDto dto) {
    boolean updated = examCommandExecutor.updateQuestionToExam(examId, questionId, dto);
    return new ResponseEntity<>(updated, HttpStatus.OK);
  }

  @PutMapping("/exam/{examId}/announcements/{announceId}")
  public ResponseEntity updateAnnouncement(
      @PathVariable int examId,
      @PathVariable int announceId,
      @Valid @RequestBody CreateOrUpdateAnnouncementDto dto) {
    examCommandExecutor.updateAnnouncementToExam(examId, announceId, dto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/exam/{id}")
  public ResponseEntity<Boolean> deleteExam(@PathVariable int id) {
    boolean deleted = examCommandExecutor.deleteExamIfNotInProgress(id);
    return new ResponseEntity<>(deleted, HttpStatus.OK);
  }

  @DeleteMapping("/exam/{examId}/questions/{questionId}")
  public ResponseEntity<Boolean> DeleteQuestion(
      @PathVariable int examId, @PathVariable int questionId) {
    Boolean deleted = examCommandExecutor.deleteQuestionFromExam(examId, questionId);
    return new ResponseEntity<>(deleted, HttpStatus.OK);
  }

  @DeleteMapping("/exam/{examId}/announcements/{announceId}")
  public ResponseEntity<Boolean> DeleteAnnouncement(
      @PathVariable int examId, @PathVariable int announceId) {
    Boolean deleted = examCommandExecutor.deleteAnnouncementFromExam(examId, announceId);
    return new ResponseEntity<>(deleted, HttpStatus.OK);
  }
}
