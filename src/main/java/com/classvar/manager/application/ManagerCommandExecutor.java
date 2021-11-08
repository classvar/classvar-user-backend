package com.classvar.manager.application;

import com.classvar.manager.application.dto.request.CreateManagerDto;
import com.classvar.manager.application.dto.request.DeleteManagersDto;
import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerCommandExecutor {

  private final ManagerRepository managerRepository;

  @Transactional
  public void createManager(long courseId, CreateManagerDto dto) {
    managerRepository.save(new Manager(courseId, dto.getEmail(), dto.getManagerId()));
  }

  @Transactional
  public void deleteManagers(long courseId, DeleteManagersDto dto) {
    managerRepository.deleteAllByIdWithCourseIdInQuery(dto.getManagersId(), courseId);
  }
}
