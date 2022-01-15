package com.classvar.course.controller;

import com.classvar.course.application.CourseQueryProcessor;
import com.classvar.course.application.dto.response.GetCourseListDto;
import com.classvar.course.application.dto.response.GetExamTakerListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @ApiOperation(value = "응시자 목록", notes = "응시자 목록을 가져옵니다.", tags = "코스 API")
  @GetMapping(value = "/{courseId}/examTakers")
  public ResponseEntity getAllExamTakerInfo(@PathVariable("courseId") long courseId) {
    GetExamTakerListDto students = courseQueryProcessor.getExamTakerList(courseId);

    return ResponseEntity.status(HttpStatus.OK).body(students);
  }
}
