package com.classvar.manager.application;

import com.classvar.manager.application.dto.response.GetManagerDto;
import com.classvar.manager.application.dto.response.GetManagerListDto;
import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import com.classvar.user.domain.User;
import com.classvar.user.domain.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerQueryProcessor {

  private final ManagerRepository managerRepository;
  private final UserRepository userRepository;

  public GetManagerListDto getManagersInfo(long courseId) {
    return new GetManagerListDto(managerRepository
        .findAllManagerInfoByCourseId(courseId).stream()
        .map(o -> {
          Manager m = (Manager) o[0];
          User u = (User) o[1];
          return new GetManagerDto(m.getId(), u.getName(),
              u.getDepartment(), m.getManagerId(), m.getEmail());
        }).collect(Collectors.toList()));
  }
}
