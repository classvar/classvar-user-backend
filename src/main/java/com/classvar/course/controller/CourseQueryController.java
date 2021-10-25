package com.classvar.course.controller;

import com.classvar.course.application.CourseQueryProcessor;
import com.classvar.course.application.dto.response.GetCourseListDto;
import com.classvar.course.application.dto.response.GetExamDetailDto;
import com.classvar.course.application.dto.response.GetExamListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseQueryController {

  private final CourseQueryProcessor courseQueryProcessor;

  @GetMapping(value = "/courses")
  public ResponseEntity getCourseAll() {

    GetCourseListDto courses = new GetCourseListDto(courseQueryProcessor.getCourseList());

    return ResponseEntity.status(HttpStatus.OK).body(courses);
  }

  @GetMapping(value = "/courses/{courseId}/exams/{examId}")
  public ResponseEntity getExamDetail(
      @PathVariable("courseId") long courseId, @PathVariable("examId") long examId) {

    GetExamDetailDto examDetail = courseQueryProcessor.getExamDetailWithId(courseId, examId);

    return ResponseEntity.status(HttpStatus.OK).body(examDetail);
  }

  @GetMapping(value = "/courses/{courseId}/exams")
  public ResponseEntity getAllExamInfo(@PathVariable("courseId") long courseId) {

    GetExamListDto exams = new GetExamListDto(courseQueryProcessor.getExamList(courseId));

    return ResponseEntity.status(HttpStatus.OK).body(exams);
  }
}
