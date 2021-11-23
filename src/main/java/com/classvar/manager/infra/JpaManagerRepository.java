package com.classvar.manager.infra;

import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaManagerRepository extends JpaRepository<Manager, Long>, ManagerRepository {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM Manager m WHERE m.id IN :ids AND m.courseId = :courseId")
  void deleteAllByIdWithCourseIdInQuery(List<Long> ids, Long courseId);

  @Query(
      "SELECT m, a FROM Manager m JOIN Admin a ON a.email = m.email WHERE m.courseId = :courseId")
  List<Object[]> findAllManagerInfoByCourseId(Long courseId);
}
