package com.classvar.course.infra;

import com.classvar.course.domain.ExamSupervisor;
import com.classvar.course.domain.ExamSupervisorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExamSupervisorRepository
    extends JpaRepository<ExamSupervisor, Long>, ExamSupervisorRepository {}
