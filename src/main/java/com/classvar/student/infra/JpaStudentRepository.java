package com.classvar.student.infra;

import com.classvar.student.domain.Student;
import com.classvar.student.domain.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Long>, StudentRepository {
}
