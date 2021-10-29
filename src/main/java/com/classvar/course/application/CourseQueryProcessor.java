package com.classvar.course.application;

import com.classvar.course.application.common.CourseMapper;
import com.classvar.course.application.dto.response.*;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.Exam;
import com.classvar.course.domain.ExamRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CourseQueryProcessor {

  private CourseRepository courseRepository;
  private ExamRepository examRepository;
  private CourseMapper courseMapper;

  public CourseQueryProcessor(
      CourseRepository courseRepository, ExamRepository examRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.examRepository = examRepository;
    this.courseMapper = courseMapper;
  }

  public List<GetCourseDto> getCourseList() {
    return courseRepository.findAll().stream()
        .map(course -> courseMapper.toCourseDto(course))
        .collect(Collectors.toList());
  }

  public GetExamDetailDto getExamDetailWithId(long courseId, long examId) {
    Exam exam =
        examRepository
            .findExamWithCourseIdAndExamId(courseId, examId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));

    return courseMapper.toExamDetailDto(exam);
  }

  public List<GetExamInfoDto> getExamList(long courseId) {
    return courseRepository.findAllExamWithCourseId(courseId).stream()
        .map(exam -> courseMapper.toExamInfoDto(exam))
        .collect(Collectors.toList());
  }

  public GetStudentListDto getStudentList(long courseId) {
    List<GetStudentDto> students =
        courseRepository.findAllStudentWithCourseId(courseId).stream()
            .map(student -> courseMapper.toStudentInfoDto(student))
            .collect(Collectors.toList());

    return new GetStudentListDto(students);
  }
}
