package com.classvar.exam.controller;

import com.classvar.common.SessionConst;
import com.classvar.course.application.CourseQueryProcessor;
import com.classvar.course.domain.ExamTaker;
import com.classvar.exam.application.ExamCommandExecutor;
import com.classvar.exam.application.dto.request.CreateOrUpdateExamDto;
import com.classvar.exam.application.dto.request.CreateOrUpdateQuestionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "시험 API")
@Controller
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamCommandController {

  private final ExamCommandExecutor examCommandExecutor;
  private final CourseQueryProcessor courseQueryProcessor;

  @ApiOperation(value = "시험 생성", notes = "시험을 생성합니다.", tags = "시험 API")
  @PostMapping
  public ResponseEntity createExam(@Valid @RequestBody CreateOrUpdateExamDto dto) {

    examCommandExecutor.createExam(dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "시험 수정", notes = "시험을 수정합니다.", tags = "시험 API")
  @PutMapping(value = "/{examId}")
  public ResponseEntity updateExam(
      @PathVariable("examId") long examId, @Valid @RequestBody CreateOrUpdateExamDto dto) {

    examCommandExecutor.updateExam(examId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "시험 삭제", notes = "시험을 삭제합니다.", tags = "시험 API")
  @DeleteMapping(value = "/{examId}")
  public ResponseEntity deleteExam(@PathVariable("examId") long examId) {

    examCommandExecutor.deleteExam(examId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "문제 생성", notes = "문제를 생성합니다.", tags = "시험 API")
  @PostMapping(value = "/{examId}/questions")
  public ResponseEntity createQuestion(
      @PathVariable("examId") Long examId, @RequestBody CreateOrUpdateQuestionDto dto) {

    examCommandExecutor.createQuestion(examId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "문제 수정", notes = "문제를 수정합니다.", tags = "시험 API")
  @PutMapping(value = "/{examId}/questions/{questionId}")
  public ResponseEntity updateQuestion(
      @PathVariable("examId") Long examId,
      @PathVariable("questionId") Long questionId,
      @RequestBody CreateOrUpdateQuestionDto dto) {

    examCommandExecutor.updateQuestion(examId, questionId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "문제 삭제", notes = "문제를 삭제합니다.", tags = "시험 API")
  @DeleteMapping(value = "/{examId}/questions/{questionId}")
  public ResponseEntity deleteQuestion(
      @PathVariable("examId") Long examId, @PathVariable("questionId") Long questionId) {

    examCommandExecutor.deleteQuestion(examId, questionId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 시험 입장", notes = "응시자가 시험에 입장합니다.", tags = "시험 API")
  @PostMapping(value = "/{examId}/students/{uuid}/join")
  public ResponseEntity joinExam(
      @PathVariable("examId") Long examId,
      @PathVariable("uuid") String uuid,
      HttpServletRequest request) {

    ExamTaker examTaker = courseQueryProcessor.getApprovedExamTaker(uuid);

    HttpSession session = request.getSession();
    session.setAttribute(SessionConst.SESSION_KEY_STUDENT, examTaker);

    // TODO 응시자가 시험장에 입장하였을 경우 StudentExamInfo를 만들어 exam에 넣어준다.

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
