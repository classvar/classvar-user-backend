package com.classvar.exam.controller;

import com.classvar.exam.application.ExamCommandExecutor;
import com.classvar.exam.application.dto.request.CreateOrUpdateExamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExamCommandController {

  private final ExamCommandExecutor examCommandExecutor;

  @PostMapping(value = "/courses/{courseId}/exams")
  public ResponseEntity createExam(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateOrUpdateExamDto dto) {

    examCommandExecutor.createExam(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PutMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity updateExam(
      @PathVariable("courseId") long courseId,
      @PathVariable("examId") long examId,
      @Valid @RequestBody CreateOrUpdateExamDto dto) {

    examCommandExecutor.updateExam(courseId, examId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity deleteExam(
      @PathVariable("courseId") long courseId, @PathVariable("examId") long examId) {

    examCommandExecutor.deleteExam(courseId, examId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
