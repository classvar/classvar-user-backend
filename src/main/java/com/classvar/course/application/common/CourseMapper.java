package com.classvar.course.application.common;

import com.classvar.course.application.dto.request.CreateExamParticipantsDto;
import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.application.dto.response.GetExamSupervisorDto;
import com.classvar.course.application.dto.response.GetExamTakerDto;
import com.classvar.course.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

  Course toCourse(CreateOrUpdateCourseDto dto);

  GetCourseDto toCourseDto(Course course);

  @Mapping(source = "participantsId", target = "studentId")
  GetExamTakerDto toExamTakerInfoDto(ExamTaker examTaker);

  @Mapping(source = "participantsId", target = "managerId")
  GetExamSupervisorDto toExamSupervisorInfoDto(ExamSupervisor examSupervisor);

  default AbstractUser toExamParticipants(CreateExamParticipantsDto dto) {
    if (dto.getRole().equals(UserRole.ExamTaker)) {
      ExamTaker examTaker =
          new ExamTaker(
              dto.getName(),
              dto.getDepartment(),
              dto.getParticipantsId(),
              dto.getEmail(),
              dto.getRole());
      return examTaker;
    }
    if (dto.getRole().equals(UserRole.ExamSupervisor)) {
      ExamSupervisor examSupervisor =
          new ExamSupervisor(
              dto.getName(),
              dto.getDepartment(),
              dto.getParticipantsId(),
              dto.getEmail(),
              dto.getRole());
      return examSupervisor;
    }
    return null;
  }

  ExamSupervisor toExamSupervisor(CreateExamParticipantsDto dto);
}
