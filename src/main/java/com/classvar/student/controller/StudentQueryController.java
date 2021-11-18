package com.classvar.student.controller;

import com.classvar.student.application.StudentQueryProcessor;
import com.classvar.student.application.dto.response.GetStudentListDto;
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

@Api(tags = "응시자 API")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentQueryController {

  private final StudentQueryProcessor studentQueryProcessor;

  @ApiOperation(value = "응시자 목록", notes = "응시자 목록을 가져옵니다.", tags = "응시자 API")
  @GetMapping(value = "/students")
  public ResponseEntity getAllStudentInfo(@RequestParam long courseId) {
    GetStudentListDto students = studentQueryProcessor.getStudentList(courseId);

    return ResponseEntity.status(HttpStatus.OK).body(students);
  }
}
