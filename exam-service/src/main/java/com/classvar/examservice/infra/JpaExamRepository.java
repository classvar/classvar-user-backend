package com.classvar.examservice.infra;

import com.classvar.examservice.domain.Announcement;
import com.classvar.examservice.domain.Exam;
import com.classvar.examservice.domain.ExamRepository;
import com.classvar.examservice.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JpaExamRepository extends JpaRepository<Exam, Integer>, ExamRepository {
  @Query("SELECT e.questions FROM Exam e WHERE e.id = :examId")
  Set<Question> findAllQuestionsById(@Param("examId") Integer id);

  @Query("SELECT e.announcements FROM Exam e WHERE e.id = :examId")
  Set<Announcement> findAllAnnouncementsById(@Param("examId") Integer id);
}
