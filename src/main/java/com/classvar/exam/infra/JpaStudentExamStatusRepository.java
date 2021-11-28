package com.classvar.exam.infra;

import com.classvar.exam.domain.StudentExamStatus;
import com.classvar.exam.domain.StudentExamStatusRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentExamStatusRepository
    extends JpaRepository<StudentExamStatus, Long>, StudentExamStatusRepository {}
