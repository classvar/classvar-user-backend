package com.classvar.student.application;

import com.classvar.student.application.common.StudentMapper;
import com.classvar.student.application.dto.request.CreateStudentsDto;
import com.classvar.student.application.dto.request.DeleteStudentsDto;
import com.classvar.student.application.dto.request.UpdateStudentInfoDto;
import com.classvar.student.application.dto.request.ApproveStudentsDto;
import com.classvar.student.domain.Student;
import com.classvar.student.domain.StudentCreatedEvent;
import com.classvar.student.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentCommandExecutor {

  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public void createStudents(CreateStudentsDto dto) {

    List<Student> students = studentMapper.toStudents(dto);
    for (Student student : students) {
      studentRepository.save(student);

      // createdEvent 발생 -> 학생에게 등록하는 url 포함된 email 전송
      eventPublisher.publishEvent(new StudentCreatedEvent(student));
    }
  }

  @Transactional
  public void updateStudent(String uuid, UpdateStudentInfoDto dto) {

    Student student = studentRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    student.updateStudentInfo(
        dto.getStudentName(), dto.getDepartment(), dto.getStudentId(), dto.getStudentEmail());
  }


  @Transactional
  public void approveStudent(ApproveStudentsDto dto) {
    studentRepository.findStudentByIdIn(dto.getStudentIds()).forEach(Student::setApproved);
  }

  @Transactional
  public void deleteStudent(DeleteStudentsDto dto) {
    studentRepository.deleteByIdIn(dto.getStudentIds());
  }
}
