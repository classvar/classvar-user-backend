package com.classvar.course.application;

import com.classvar.course.application.common.EntityMapper;
import com.classvar.course.application.dto.request.*;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.Exam;
import com.classvar.course.domain.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class CourseCommandExecutor {

  private CourseRepository courseRepository;
  private EntityMapper entityMapper;

  public CourseCommandExecutor(CourseRepository courseRepository, EntityMapper entityMapper) {
    this.courseRepository = courseRepository;
    this.entityMapper = entityMapper;
  }

  @Transactional
  public long createCourse(CreateOrUpdateCourseDto dto) {
    Course course = entityMapper.toCourse(dto);
    courseRepository.save(course);
    return course.getId();
  }

  @Transactional
  public boolean updateCourse(long courseId, CreateOrUpdateCourseDto dto) {
    Course course =
        courseRepository
            .findCourseById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));
    course.changeCourseName(dto.getName());
    return true;
  }

  @Transactional
  public boolean deleteCourse(long courseId) {
    Course course =
        courseRepository
            .findCourseById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));

    courseRepository.delete(course);
    return true;
  }

  @Transactional
  public void createExamToCourse(long courseId, CreateOrUpdateExamDto dto) {
    Course course =
        courseRepository
            .findCourseById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));

    Exam exam = entityMapper.toExam(dto);

    course.addExam(exam);
  }

  @Transactional
  public void updateExamToCourse(long courseId, long examId, CreateOrUpdateExamDto dto) {
    Exam exam =
        courseRepository.findAllExamWithCourseId(courseId).stream()
            .filter(e -> e.getId() == examId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시험 입니다."));

    exam.updateExamInfo(dto.getName(), dto.getExamDate(), dto.getStartTime(), dto.getEndTime());
  }

  @Transactional
  public void deleteExamToCourse(long courseId, long examId) {
    Course course =
        courseRepository
            .findCourseById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));

    course.deleteExam(examId);
  }

  @Transactional
  public void createStudentsToCourse(long courseId, CreateStudentsDto dto){
    Course course =
            courseRepository
                    .findCourseById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));

    dto.getEmails().forEach(email -> course.addStudent(new Student(email)));

    //createdEvent 발생 -> 학생에게 등록하는 url 포함된 email 전송
  }

  @Transactional
  public void updateStudentToCourse(long courseId, String uuid, UpdateStudentInfoDto dto){
    Student student =
            courseRepository.findAllStudentWithCourseId(courseId).stream()
                    .filter(s -> s.getUuid().equals(uuid))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    student.updateStudentInfo(dto.getStudentName(), dto.getDepartment(), dto.getStudentId(), dto.getStudentEmail());
  }

  @Transactional
  public void approveStudentToCourse(long courseId, VerifyOrDeleteStudentsDto dto){
    Course course =
            courseRepository
                    .findCourseById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));

    dto.getStudents().forEach(id -> course.approveStudent(id));

    //approvedEvent 발생 -> 학생에게 시험장 url 포함된 email 전송
  }

  @Transactional
  public void deleteStudentToCourse(long courseId, VerifyOrDeleteStudentsDto dto){
    Course course =
            courseRepository
                    .findCourseById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코스 입니다."));

    dto.getStudents().forEach(id -> course.deleteStudent(id));
  }
}
