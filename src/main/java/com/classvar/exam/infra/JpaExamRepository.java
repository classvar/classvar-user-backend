package com.classvar.exam.infra;

import com.classvar.exam.domain.Exam;
import com.classvar.exam.domain.ExamRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExamRepository extends JpaRepository<Exam, Long>, ExamRepository {}
