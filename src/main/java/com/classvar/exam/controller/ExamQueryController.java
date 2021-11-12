package com.classvar.exam.controller;

import com.classvar.exam.application.ExamQueryProcessor;
import com.classvar.exam.application.dto.response.GetExamDetailDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "시험 API")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExamQueryController {

  private final ExamQueryProcessor examQueryProcessor;

  @ApiOperation(value = "시험 정보", notes = "시험 정보를 가져옵니다.", tags = "시험 API")
  @GetMapping(value = "/exams/{examId}")
  public ResponseEntity getExamDetail(
      @RequestParam long courseId, @PathVariable("examId") long examId) {

    GetExamDetailDto examDetail = examQueryProcessor.getExamDetailWithId(courseId, examId);

    return ResponseEntity.status(HttpStatus.OK).body(examDetail);
  }

  @ApiOperation(value = "시험 목록", notes = "시험 목록을 가져옵니다.", tags = "시험 API")
  @GetMapping(value = "/exams")
  public ResponseEntity getAllExamInfo(@RequestParam long courseId) {

    return ResponseEntity.status(HttpStatus.OK).body(examQueryProcessor.getExamList(courseId));
  }

  @ApiOperation(value = "문제 목록", notes = "해당 시험의 문제 목록을 가져옵니다.", tags = "시험 API")
  @GetMapping(value = "/exams/{examId}/questions")
  public ResponseEntity getAllQuestion(
      @RequestParam long courseId, @PathVariable("examId") long examId) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(examQueryProcessor.getQuestionList(courseId, examId));
  }
}
