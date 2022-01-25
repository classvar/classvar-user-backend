package com.classvar.course.infra;

import com.classvar.course.domain.ExamSupervisor;
import com.classvar.course.domain.ReadOnlyExamSupervisorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReadOnlyExamSupervisorRepository
    extends JpaRepository<ExamSupervisor, Long>, ReadOnlyExamSupervisorRepository {}
