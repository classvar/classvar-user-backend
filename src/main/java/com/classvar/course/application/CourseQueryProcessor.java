package com.classvar.course.application;

import com.classvar.course.application.common.CourseMapper;
import com.classvar.course.application.dto.response.*;
import com.classvar.course.domain.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CourseQueryProcessor {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public List<GetCourseDto> getCourseList() {
    return courseRepository.findAll().stream()
        .map(course -> courseMapper.toCourseDto(course))
        .collect(Collectors.toList());
  }
}
