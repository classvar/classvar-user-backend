package com.classvar.course.application;

import com.classvar.course.application.common.CourseMapper;
import com.classvar.course.application.dto.response.*;
import com.classvar.course.domain.*;
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
  private final ExamTakerRepository examTakerRepository;
  private final ExamSupervisorRepository examSupervisorRepository;
  private final CourseMapper courseMapper;

  public List<GetCourseDto> getCourseList() {
    return courseRepository.findAll().stream()
        .map(course -> courseMapper.toCourseDto(course))
        .collect(Collectors.toList());
  }

  public GetExamTakerListDto getAllExamTakersOfCourse(long courseId) {
    List<GetExamTakerDto> examTakers =
        examTakerRepository.findAllExamTakersByCourseId(courseId).stream()
            .map(courseMapper::toExamTakerInfoDto)
            .collect(Collectors.toList());

    return new GetExamTakerListDto(examTakers);
  }

  public ExamTaker getApprovedExamTaker(String uuid) {
    ExamTaker examTaker =
        examTakerRepository
            .findByUuid(uuid)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    if (!examTaker.getApproved()) {
      throw new IllegalArgumentException("승인 대기중 입니다.");
    }

    return examTaker;
  }

  public GetExamSupervisorListDto getAllExamSupervisorsOfCourse(long courseId) {
    List<GetExamSupervisorDto> examSupervisors =
        examSupervisorRepository.findAllExamSupervisorsByCourseId(courseId).stream()
            .map(courseMapper::toExamSupervisorInfoDto)
            .collect(Collectors.toList());

    return new GetExamSupervisorListDto(examSupervisors);
  }

  public ExamSupervisor getApprovedExamSupervisor(String uuid) {
    ExamSupervisor examSupervisor =
        examSupervisorRepository
            .findByUuid(uuid)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 감독관 입니다."));

    if (!examSupervisor.getApproved()) {
      throw new IllegalArgumentException("승인 대기중 입니다.");
    }

    return examSupervisor;
  }
}
