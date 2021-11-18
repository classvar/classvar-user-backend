package com.classvar.student.application;

import com.classvar.student.application.common.StudentMapper;
import com.classvar.student.application.dto.response.GetStudentDto;
import com.classvar.student.application.dto.response.GetStudentListDto;
import com.classvar.student.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudentQueryProcessor {

  private final StudentMapper studentMapper;
  private final StudentRepository studentRepository;

  public GetStudentListDto getStudentList(long courseId) {
    List<GetStudentDto> students = studentRepository.findStudentByCourseId(courseId).stream()
            .map(studentMapper::toStudentInfoDto)
            .collect(Collectors.toList());

    return new GetStudentListDto(students);
  }
}
