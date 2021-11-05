package com.classvar.manager.infra;

import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaManagerRepository extends JpaRepository<Manager, Long>, ManagerRepository {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM Manager m WHERE m.id IN :ids AND m.courseId = :courseId")
  void deleteAllByIdWithCourseIdInQuery(List<Long> ids, Long courseId);
}