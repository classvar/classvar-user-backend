package com.classvar.course.controller;

import com.classvar.course.application.CourseQueryProcessor;
import com.classvar.course.application.dto.response.GetCourseListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
