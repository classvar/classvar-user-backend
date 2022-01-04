package com.classvar.manager.controller;

import com.classvar.manager.application.ManagerCommandExecutor;
import com.classvar.manager.application.dto.request.ApproveManagerDto;
import com.classvar.manager.application.dto.request.CreateManagerDto;
import com.classvar.manager.application.dto.request.DeleteManagerDto;
import com.classvar.manager.application.dto.request.UpdateManagerInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "감독관 API")
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerCommandController {

  private final ManagerCommandExecutor managerCommandExecutor;

  @ApiOperation(value = "감독관 생성", notes = "감독관을 생성합니다.", tags = "감독관 API")
  @PostMapping
  public ResponseEntity createManager(@RequestBody @Valid CreateManagerDto dto) {
    managerCommandExecutor.createManager(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "감독관 정보 등록", notes = "감독관 정보를 등록합니다.", tags = "감독관 API")
  @PutMapping(value = "/registry/{uuid}")
  public ResponseEntity registerManager(
      @PathVariable("uuid") String uuid, @Valid @RequestBody UpdateManagerInfoDto dto) {

    managerCommandExecutor.updateManager(uuid, dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "감독관 승인", notes = "감독관을 승인합니다.", tags = "감독관 API")
  @PostMapping(value = "/verify")
  public ResponseEntity approveManagers(@Valid @RequestBody ApproveManagerDto dto) {

    managerCommandExecutor.approveManager(dto);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  // 다수의 감독관을 지우기 위해 delete 동사를 덧붙여 Post 형태로 받음.
  @ApiOperation(value = "감독관 삭제", notes = "괌독관을 삭제합니다.", tags = "감독관 API")
  @PostMapping(value = "/delete")
  public ResponseEntity deleteManagers(@RequestBody @Valid DeleteManagerDto dto) {
    managerCommandExecutor.deleteManager(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
