package com.classvar.manager.application;

import com.classvar.manager.application.common.ManagerMapper;
import com.classvar.manager.application.dto.request.ApproveManagerDto;
import com.classvar.manager.application.dto.request.CreateManagerDto;
import com.classvar.manager.application.dto.request.DeleteManagerDto;
import com.classvar.manager.application.dto.request.UpdateManagerInfoDto;
import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerCreatedEvent;
import com.classvar.manager.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerCommandExecutor {

  private final ManagerRepository managerRepository;
  private final ManagerMapper managerMapper;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public void createManager(CreateManagerDto dto){
    List<Manager> managers = managerMapper.toManagers(dto);
    for (Manager manager : managers) {
      managerRepository.save(manager);

      eventPublisher.publishEvent(new ManagerCreatedEvent(manager));
    }
  }

  public void updateManager(String uuid, UpdateManagerInfoDto dto) {

    Manager manager = managerRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 응시자 입니다."));

    manager.updateManagerInfo(
            dto.getManagerName(), dto.getManagerId(), dto.getManagerEmail());
  }

  @Transactional
  public void approveManager(ApproveManagerDto dto) {
    managerRepository.findManagerByIdIn(dto.getManagerIds()).forEach(Manager::setApproved);
  }

  @Transactional
  public void deleteManager(DeleteManagerDto dto) {
    managerRepository.deleteByIdIn(dto.getManagerIds());
  }


}
