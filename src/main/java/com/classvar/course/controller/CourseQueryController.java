package com.classvar.course.controller;

import com.classvar.course.application.CourseQueryProcessor;
import com.classvar.course.application.dto.response.GetCourseListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "코스 API")
@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseQueryController {

  private final CourseQueryProcessor courseQueryProcessor;

  @ApiOperation(value = "코스 목록", notes = "코스 목록을 가져옵니다.", tags = "코스 API")
  @GetMapping
  public ResponseEntity getCourseAll() {

    GetCourseListDto courses = new GetCourseListDto(courseQueryProcessor.getCourseList());

    return ResponseEntity.status(HttpStatus.OK).body(courses);
  }
}
