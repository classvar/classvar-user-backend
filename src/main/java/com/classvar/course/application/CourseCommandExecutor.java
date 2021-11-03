package com.classvar.course.application;

import com.classvar.course.application.common.CourseMapper;
import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseCommandExecutor {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public Course findByCourseId(long courseId){
    return courseRepository
                    .findCourseById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));
  }
  @Transactional
  public long createCourse(CreateOrUpdateCourseDto dto) {
    Course course = courseMapper.toCourse(dto);
    courseRepository.save(course);
    return course.getId();
  }

  @Transactional
  public boolean updateCourse(long courseId, CreateOrUpdateCourseDto dto) {
    Course course = findByCourseId(courseId);
    course.changeCourseName(dto.getName());
    return true;
  }

  @Transactional
  public boolean deleteCourse(long courseId) {
    Course course = findByCourseId(courseId);
    courseRepository.delete(course);
    return true;
  }
}
