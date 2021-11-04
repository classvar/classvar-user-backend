package com.classvar.exam.controller;

import com.classvar.exam.application.ExamCommandExecutor;
import com.classvar.exam.application.dto.request.CreateOrUpdateExamDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "시험 API")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExamCommandController {

  private final ExamCommandExecutor examCommandExecutor;

  @ApiOperation(value = "시험 생성", notes = "시험을 생성합니다.", tags = "시험 API")
  @PostMapping(value = "/courses/{courseId}/exams")
  public ResponseEntity createExam(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateOrUpdateExamDto dto) {

    examCommandExecutor.createExam(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "시험 수정", notes = "시험을 수정합니다.", tags = "시험 API")
  @PutMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity updateExam(
      @PathVariable("courseId") long courseId,
      @PathVariable("examId") long examId,
      @Valid @RequestBody CreateOrUpdateExamDto dto) {

    examCommandExecutor.updateExam(courseId, examId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "시험 삭제", notes = "시험을 삭제합니다.", tags = "시험 API")
  @DeleteMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity deleteExam(
      @PathVariable("courseId") long courseId, @PathVariable("examId") long examId) {

    examCommandExecutor.deleteExam(courseId, examId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
