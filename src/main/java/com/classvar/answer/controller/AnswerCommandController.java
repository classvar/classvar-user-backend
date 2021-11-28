package com.classvar.answer.controller;

import com.classvar.answer.application.AnswerCommandExecutor;
import com.classvar.answer.application.dto.request.SubmitAnswerDto;
import com.classvar.common.argumentresolver.StudentParam;
import com.classvar.student.domain.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "답안 API")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnswerCommandController {

  private final AnswerCommandExecutor answerCommandExecutor;

  @ApiOperation(value = "응시자 답안 제출", notes = "응시자가 답안을 제출합니다.", tags = "답안 API")
  @PostMapping(value = "/answers/submit")
  public ResponseEntity submitAnswer(
      @RequestBody SubmitAnswerDto dto, @StudentParam Student student) {

    answerCommandExecutor.submitAnswer(dto, student);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
