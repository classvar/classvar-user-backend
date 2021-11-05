package com.classvar.student.controller;

import com.classvar.student.application.StudentCommandExecutor;
import com.classvar.student.application.dto.request.CreateStudentsDto;
import com.classvar.student.application.dto.request.UpdateStudentInfoDto;
import com.classvar.student.application.dto.request.VerifyOrDeleteStudentsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "응시자 API")
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentCommandController {

  private final StudentCommandExecutor studentCommandExecutor;

  @ApiOperation(value = "응시자 생성", notes = "응시자를 생성합니다.", tags = "응시자 API")
  @PostMapping(value = "/courses/{courseId}/students")
  public ResponseEntity createStudent(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateStudentsDto dto) {

    studentCommandExecutor.createStudents(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 정보 등록", notes = "응시자 정보를 등록합니다.", tags = "응시자 API")
  @PutMapping(value = "courses/{courseId}/students/registry/{uuid}")
  public ResponseEntity registerStudent(
      @PathVariable("courseId") long courseId,
      @PathVariable("uuid") String uuid,
      @Valid @RequestBody UpdateStudentInfoDto dto) {

    studentCommandExecutor.updateStudent(courseId, uuid, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 승인", notes = "응시자를 승인합니다.", tags = "응시자 API")
  @PostMapping(value = "courses/{courseId}/students/verify")
  public ResponseEntity approveStudents(
      @PathVariable("courseId") long courseId, @Valid @RequestBody VerifyOrDeleteStudentsDto dto) {

    studentCommandExecutor.approveStudent(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 삭제", notes = "응시자를 삭제합니다.", tags = "응시자 API")
  @PostMapping(value = "/courses/{courseId}/students/delete")
  public ResponseEntity deleteStudents(
      @PathVariable("courseId") long courseId, @Valid @RequestBody VerifyOrDeleteStudentsDto dto) {

    studentCommandExecutor.deleteStudent(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
