package com.classvar.manager.domain;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {

  Manager save(Manager manager);

  void delete(Manager manager);

  void deleteByIdIn(List<Long> managerIds);

  Optional<Manager> findByUuid(String uuid);

  List<Manager> findManagerByCourseId(Long courseId);

  List<Manager> findManagerByIdIn(List<Long> managerIds);
}
