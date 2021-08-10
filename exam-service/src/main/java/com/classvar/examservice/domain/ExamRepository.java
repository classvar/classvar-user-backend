package com.classvar.examservice.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExamRepository {
  Optional<Exam> findById(Integer id);

  List<Exam> findAll();

  Exam save(Exam toSave);

  void delete(Exam toRemove);

  void deleteAll();

  Set<Question> findAllQuestionsById(Integer id);

  Set<Announcement> findAllAnnouncementsById(Integer id);
}
