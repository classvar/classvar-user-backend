package com.classvar.student.application.common;

import com.classvar.student.application.dto.request.CreateStudentsDto;
import com.classvar.student.application.dto.response.GetStudentDto;
import com.classvar.student.domain.Student;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  GetStudentDto toStudentInfoDto(Student student);

  default List<Student> toStudents(CreateStudentsDto dto) {
    List<Student> students = new ArrayList<>();
    for (String email : dto.getEmails()) {
      students.add(new Student(dto.getCourseId(), email));
    }

    return students;
  }
}
