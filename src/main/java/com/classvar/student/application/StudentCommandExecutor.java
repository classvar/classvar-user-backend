package com.classvar.student.application;

import com.classvar.student.application.common.StudentMapper;
import com.classvar.student.application.dto.request.CreateStudentsDto;
import com.classvar.student.application.dto.request.UpdateStudentInfoDto;
import com.classvar.student.application.dto.request.VerifyOrDeleteStudentsDto;
import com.classvar.student.domain.Student;
import com.classvar.student.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentCommandExecutor {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  @Transactional
  public void createStudents(CreateStudentsDto dto) {
    studentMapper.toStudents(dto).forEach(studentRepository::save);

    // createdEvent 발생 -> 학생에게 등록하는 url 포함된 email 전송
  }

  @Transactional
  public void updateStudent(String uuid, UpdateStudentInfoDto dto) {

    Student student = studentRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    student.updateStudentInfo(
        dto.getStudentName(), dto.getDepartment(), dto.getStudentId(), dto.getStudentEmail());
  }


  @Transactional
  public void approveStudent(VerifyOrDeleteStudentsDto dto) {
    studentRepository.findStudentByIdIn(dto.getStudentIds()).forEach(Student::verified);
  }

  @Transactional
  public void deleteStudent(VerifyOrDeleteStudentsDto dto) {
    studentRepository.deleteByIdIn(dto.getStudentIds());
  }
}
