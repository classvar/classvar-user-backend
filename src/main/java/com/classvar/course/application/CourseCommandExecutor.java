package com.classvar.course.application;

import com.classvar.course.application.common.CourseMapper;
import com.classvar.course.application.dto.request.*;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.ExamSupervisor;
import com.classvar.course.domain.ExamTaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseCommandExecutor {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  private Course findByCourseId(long courseId) {
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

  @Transactional
  public void createExamTakers(long courseId, CreateExamParticipantsDto dto) {
    Course course = findByCourseId(courseId);

    ExamTaker examTaker = (ExamTaker) courseMapper.toExamParticipants(dto);

    course.addExamParticipants(examTaker);
  }

  @Transactional
  public void approveExamTaker(long courseId, ApproveExamTakerDto dto) {
    Course course = findByCourseId(courseId);

    for (Long examTakerId : dto.getExamTakerIds()) {
      course.approveExamTaker(examTakerId);

      // createdEvent 발생 -> 학생에게 시험장 url 포함된 email 전송
    }
  }

  @Transactional
  public void deleteExamTaker(long courseId, DeleteExamTakerDto dto) {
    Course course = findByCourseId(courseId);

    for (Long examTakerId : dto.getExamTakerIds()) {
      course.removeExamTaker(examTakerId);
    }
  }

  @Transactional
  public void createExamSupervisor(long courseId, CreateExamParticipantsDto dto) {
    Course course = findByCourseId(courseId);

    ExamSupervisor examSupervisor = (ExamSupervisor) courseMapper.toExamParticipants(dto);

    course.addExamParticipants(examSupervisor);
  }

  @Transactional
  public void approveExamSupervisor(long courseId, ApproveExamSupervisorDto dto) {
    Course course = findByCourseId(courseId);

    for (Long examSupervisorId : dto.getExamSupervisorIds()) {
      course.approveSupervisor(examSupervisorId);

      // createdEvent 발생 -> 학생에게 시험장 url 포함된 email 전송
    }
  }

  @Transactional
  public void deleteExamSupervisor(long courseId, DeleteExamSupervisorDto dto) {
    Course course = findByCourseId(courseId);

    for (Long examSupervisorId : dto.getExamSupervisorIds()) {
      course.removeExamTaker(examSupervisorId);
    }
  }
}
