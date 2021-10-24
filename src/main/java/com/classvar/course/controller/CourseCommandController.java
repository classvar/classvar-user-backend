package com.classvar.course.controller;

import com.classvar.course.application.CourseCommandExecutor;
import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.request.CreateOrUpdateExamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseCommandController {

  private final CourseCommandExecutor courseCommandExecutor;

  @PostMapping(value = "/courses")
  public ResponseEntity createCourse(@Valid @RequestBody CreateOrUpdateCourseDto dto) {

    courseCommandExecutor.createCourse(dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PutMapping(value = "/courses/{courseId}")
  public ResponseEntity createCourse(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateOrUpdateCourseDto dto) {

    courseCommandExecutor.updateCourse(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(value = "/courses/{courseId}")
  public ResponseEntity deleteCourse(@PathVariable("courseId") long courseId) {

    courseCommandExecutor.deleteCourse(courseId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/courses/{courseId}/exams")
  public ResponseEntity createExam(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateOrUpdateExamDto dto) {

    courseCommandExecutor.createExamToCourse(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PutMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity updateExam(
      @PathVariable("courseId") long courseId,
      @PathVariable("examId") long examId,
      @Valid @RequestBody CreateOrUpdateExamDto dto) {

    courseCommandExecutor.updateExamToCourse(courseId, examId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity deleteExam(
      @PathVariable("courseId") long courseId, @PathVariable("examId") long examId) {

    courseCommandExecutor.deleteExamToCourse(courseId, examId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
