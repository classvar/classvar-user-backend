package com.classvar.manager.application;

import com.classvar.manager.application.dto.response.GetManagerDto;
import com.classvar.manager.application.dto.response.GetManagerListDto;
import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import com.classvar.user.domain.User;
import com.classvar.user.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerQueryProcessor {

  private final ManagerRepository managerRepository;
  private final UserRepository userRepository;

  public GetManagerListDto getManagerList(long courseId) {
    List<Manager> managers = managerRepository.findAllByCourseId(courseId);
    List<User> managersInfo = userRepository.findAllByEmails(managers.stream().
        map(m -> m.getEmail()).collect(Collectors.toList()));
    List<GetManagerDto> result = new ArrayList<>();
    for (int i = 0; i < managers.size(); i++) {
      result.add(new GetManagerDto(managers.get(i).getId(), managersInfo.get(i).getName(),
          managersInfo.get(i).getDepartment(),
          managers.get(i).getManagerId(), managers.get(i).getEmail()));
    }
    return new GetManagerListDto(result);
  }
}
