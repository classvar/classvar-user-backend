package com.classvar.manager.domain;

import java.util.List;

public interface ManagerRepository {

  Manager save(Manager manager);

  List<Manager> findAllByCourseId(long courseId);

  void deleteAllByIdWithCourseIdInQuery(List<Long> ids, Long courseId);
}
