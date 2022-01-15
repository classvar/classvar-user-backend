package com.classvar.course.infra;

import com.classvar.course.domain.ExamTaker;
import com.classvar.course.domain.ExamTakerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExamTakerRepository
    extends JpaRepository<ExamTaker, Long>, ExamTakerRepository {}
