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
  public void createStudents(long courseId, CreateStudentsDto dto) {
    studentMapper.toStudents(dto).forEach(studentRepository::save);

    // createdEvent 발생 -> 학생에게 등록하는 url 포함된 email 전송
  }

  @Transactional
  public void updateStudent(long courseId, String uuid, UpdateStudentInfoDto dto) {

    Student student = studentRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    student.updateStudentInfo(
        dto.getStudentName(), dto.getDepartment(), dto.getStudentId(), dto.getStudentEmail());
  }


  @Transactional
  public void approveStudent(long courseId, VerifyOrDeleteStudentsDto dto) {

    //"select student s from student where sid in list"
    studentRepository.findStudentWithIds(dto.getStudents()).forEach(Student::verify);

    //응시자 승인 -> 모든 치뤄지지 않은 시험에서 StudentExamInfo 생성

  }

  @Transactional
  public void deleteStudent(long courseId, VerifyOrDeleteStudentsDto dto) {
    studentRepository.findStudentWithIds(dto.getStudents()).forEach(studentRepository::delete);
  }
}
