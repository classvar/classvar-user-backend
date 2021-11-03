package com.classvar.student.controller;

import com.classvar.student.application.StudentCommandExecutor;
import com.classvar.student.application.dto.request.CreateStudentsDto;
import com.classvar.student.application.dto.request.UpdateStudentInfoDto;
import com.classvar.student.application.dto.request.VerifyOrDeleteStudentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentCommandController {

  private final StudentCommandExecutor studentCommandExecutor;

  @PostMapping(value = "/courses/{courseId}/students")
  public ResponseEntity createStudent(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateStudentsDto dto) {

    studentCommandExecutor.createStudents(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PutMapping(value = "courses/{courseId}/students/registry/{uuid}")
  public ResponseEntity registerStudent(
      @PathVariable("courseId") long courseId,
      @PathVariable("uuid") String uuid,
      @Valid @RequestBody UpdateStudentInfoDto dto) {

    studentCommandExecutor.updateStudent(courseId, uuid, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "courses/{courseId}/students/verify")
  public ResponseEntity approveStudents(
      @PathVariable("courseId") long courseId, @Valid @RequestBody VerifyOrDeleteStudentsDto dto) {

    studentCommandExecutor.approveStudent(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/courses/{courseId}/students/delete")
  public ResponseEntity deleteStudents(
      @PathVariable("courseId") long courseId, @Valid @RequestBody VerifyOrDeleteStudentsDto dto) {

    studentCommandExecutor.deleteStudent(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
