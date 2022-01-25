package com.classvar.course.infra;

import com.classvar.course.domain.ExamTaker;
import com.classvar.course.domain.ReadOnlyExamTakerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReadOnlyExamTakerRepository
    extends JpaRepository<ExamTaker, Long>, ReadOnlyExamTakerRepository {}
