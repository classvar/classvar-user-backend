package com.classvar.course.application;

import com.classvar.course.application.common.EntityMapper;
import com.classvar.course.application.dto.response.*;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.Exam;
import com.classvar.course.domain.ExamRepository;
import com.classvar.course.domain.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CourseQueryProcessor {

  private CourseRepository courseRepository;
  private ExamRepository examRepository;
  private EntityMapper entityMapper;

  public CourseQueryProcessor(
      CourseRepository courseRepository, ExamRepository examRepository, EntityMapper entityMapper) {
    this.courseRepository = courseRepository;
    this.examRepository = examRepository;
    this.entityMapper = entityMapper;
  }

  public List<GetCourseDto> getCourseList() {
    return courseRepository.findAll().stream()
        .map(course -> entityMapper.toCourseDto(course))
        .collect(Collectors.toList());
  }

  public GetExamDetailDto getExamDetailWithId(long courseId, long examId) {
    Exam exam =
        examRepository
            .findExamWithCourseIdAndExamId(courseId, examId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));

    return entityMapper.toExamDetailDto(exam);
  }

  public List<GetExamInfoDto> getExamList(long courseId) {
    return courseRepository.findAllExamWithCourseId(courseId).stream()
        .map(exam -> entityMapper.toExamInfoDto(exam))
        .collect(Collectors.toList());
  }

  public GetStudentListDto getStudentList(long courseId){
    List<GetStudentDto> students = courseRepository.findAllStudentWithCourseId(courseId).stream()
            .map(student -> entityMapper.toStudentInfoDto(student))

            .collect(Collectors.toList());

    return new GetStudentListDto(students);
  }
}
