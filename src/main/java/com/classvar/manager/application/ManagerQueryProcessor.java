package com.classvar.manager.application;

import com.classvar.admin.domain.Admin;
import com.classvar.manager.application.dto.response.GetManagerDto;
import com.classvar.manager.application.dto.response.GetManagerListDto;
import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerQueryProcessor {

  private final ManagerRepository managerRepository;

  public GetManagerListDto getManagersInfo(long courseId) {
    return new GetManagerListDto(
        managerRepository.findAllManagerInfoByCourseId(courseId).stream()
            .map(
                o -> {
                  Manager m = (Manager) o[0];
                  Admin u = (Admin) o[1];
                  return new GetManagerDto(
                      m.getId(), u.getName(), u.getDepartment(), m.getManagerId(), m.getEmail());
                })
            .collect(Collectors.toList()));
  }
}
