package com.classvar.course.infra;

import com.classvar.course.domain.StudentExamInfo;
import com.classvar.course.domain.StudentExamInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentExamInfoRepository extends JpaRepository<StudentExamInfo, Long>, StudentExamInfoRepository {
}
