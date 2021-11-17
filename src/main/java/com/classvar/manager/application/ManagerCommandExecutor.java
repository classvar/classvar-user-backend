package com.classvar.manager.application;

import com.classvar.manager.application.common.ManagerMapper;
import com.classvar.manager.application.dto.request.CreateManagerDto;
import com.classvar.manager.application.dto.request.UpdateManagerInfoDto;
import com.classvar.manager.application.dto.request.VerifyOrDeleteManagerDto;
import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerCommandExecutor {

  private final ManagerRepository managerRepository;
  private final ManagerMapper managerMapper;

  @Transactional
  public void createManager(CreateManagerDto dto) {
    managerMapper.toManagers(dto).forEach(managerRepository::save);
  }

  public void updateManager(String uuid, UpdateManagerInfoDto dto) {

    Manager manager = managerRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    manager.updateManagerInfo(
            dto.getManagerName(), dto.getManagerId(), dto.getManagerEmail());
  }

  @Transactional
  public void approveManager(VerifyOrDeleteManagerDto dto) {
    managerRepository.findManagerByIdIn(dto.getManagerIds()).forEach(Manager::verified);
  }

  @Transactional
  public void deleteManager(VerifyOrDeleteManagerDto dto) {
    managerRepository.deleteByIdIn(dto.getManagerIds());
  }


}
