package com.classvar.student.application.common;

import com.classvar.student.application.dto.request.CreateStudentsDto;
import com.classvar.student.application.dto.response.GetStudentDto;
import com.classvar.student.domain.Student;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  GetStudentDto toStudentInfoDto(Student student);

  default List<Student> toStudents(CreateStudentsDto dto) {
    return dto.getEmails().stream().map(Student::new).collect(Collectors.toList());
  }
}
