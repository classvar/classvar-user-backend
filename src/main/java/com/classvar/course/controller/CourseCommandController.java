package com.classvar.course.controller;

import com.classvar.course.application.CourseCommandExecutor;
import com.classvar.course.application.dto.request.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "코스 API")
@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseCommandController {

  private final CourseCommandExecutor courseCommandExecutor;

  @ApiOperation(value = "코스 생성", notes = "코스를 생성합니다.", tags = "코스 API")
  @PostMapping
  public ResponseEntity createCourse(@Valid @RequestBody CreateOrUpdateCourseDto dto) {

    courseCommandExecutor.createCourse(dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "코스 수정", notes = "코스를 수정합니다.", tags = "코스 API")
  @PutMapping(value = "/{courseId}")
  public ResponseEntity updateCourse(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateOrUpdateCourseDto dto) {

    courseCommandExecutor.updateCourse(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "코스 삭제", notes = "코스를 삭제합니다.", tags = "코스 API")
  @DeleteMapping(value = "/{courseId}")
  public ResponseEntity deleteCourse(@PathVariable("courseId") long courseId) {

    courseCommandExecutor.deleteCourse(courseId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 정보 등록", notes = "응시자 정보를 등록합니다.", tags = "코스 API")
  @PostMapping(value = "/{courseId}/examTakers/registry")
  public ResponseEntity createExamTaker(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateExamParticipantsDto dto) {

    courseCommandExecutor.createExamTakers(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 삭제", notes = "응시자를 삭제합니다.", tags = "코스 API")
  @PostMapping(value = "/{courseId}/examTakers/delete")
  public ResponseEntity deleteExamTakers(
      @PathVariable("courseId") long courseId, @Valid @RequestBody DeleteExamTakerDto dto) {

    courseCommandExecutor.deleteExamTaker(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "응시자 승인", notes = "응시자를 승인합니다.", tags = "코스 API")
  @PostMapping(value = "/{courseId}/examTakers/verify")
  public ResponseEntity approveExamTakers(
      @PathVariable("courseId") long courseId, @Valid @RequestBody ApproveExamTakerDto dto) {

    courseCommandExecutor.approveExamTaker(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "감독관 정보 등록", notes = "감독관 정보를 등록합니다.", tags = "코스 API")
  @PostMapping("/{courseId}/examSupervisors/registry")
  public ResponseEntity createExamSupervisor(
      @PathVariable("courseId") long courseId, @Valid @RequestBody CreateExamParticipantsDto dto) {
    courseCommandExecutor.createExamSupervisor(courseId, dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "감독관 승인", notes = "감독관을 승인합니다.", tags = "코스 API")
  @PostMapping(value = "/{courseId}/examSupervisors/verify")
  public ResponseEntity approveExamSupervisors(
      @PathVariable("courseId") long courseId, @Valid @RequestBody ApproveExamSupervisorDto dto) {

    courseCommandExecutor.approveExamSupervisor(courseId, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  // 다수의 감독관을 지우기 위해 delete 동사를 덧붙여 Post 형태로 받음.
  @ApiOperation(value = "감독관 삭제", notes = "괌독관을 삭제합니다.", tags = "코스 API")
  @PostMapping(value = "/{courseId}/examSupervisors/delete")
  public ResponseEntity deleteExamSupervisors(
      @PathVariable("courseId") long courseId, @RequestBody @Valid DeleteExamSupervisorDto dto) {
    courseCommandExecutor.deleteExamSupervisor(courseId, dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
