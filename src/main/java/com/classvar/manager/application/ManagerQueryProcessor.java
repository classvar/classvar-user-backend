package com.classvar.manager.application;

import com.classvar.manager.application.common.ManagerMapper;
import com.classvar.manager.application.dto.response.GetManagerDto;
import com.classvar.manager.application.dto.response.GetManagerListDto;
import com.classvar.manager.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerQueryProcessor {

  private final ManagerRepository managerRepository;
  private final ManagerMapper managerMapper;

  public GetManagerListDto getManagerList(long courseId) {
      List<GetManagerDto> managers = managerRepository.findManagerByCourseId(courseId).stream()
              .map(managerMapper::toManagerInfoDto)
              .collect(Collectors.toList());

      return new GetManagerListDto(managers);
  }
}
