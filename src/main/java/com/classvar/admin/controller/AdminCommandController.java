package com.classvar.admin.controller;

import com.classvar.admin.application.AdminCommandExecutor;
import com.classvar.admin.application.dto.request.CreateOrUpdateAdminDto;
import com.classvar.admin.application.dto.request.LoginDto;
import com.classvar.admin.application.dto.response.GetAdminInfoDto;
import com.classvar.common.SessionConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "회원 API")
@Controller
@RequiredArgsConstructor
public class AdminCommandController {

  private final AdminCommandExecutor adminCommandExecutor;

  @ApiOperation(value = "회원 가입", notes = "회원 가입을 합니다.", tags = "회원 API")
  @PostMapping(value = "/signup")
  public ResponseEntity signUp(@Valid @RequestBody CreateOrUpdateAdminDto dto) {
    adminCommandExecutor.signUp(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "회원 로그인", notes = "로그인을 합니다.", tags = "회원 API")
  @PostMapping(value = "/login")
  public ResponseEntity login(@Valid @RequestBody LoginDto dto, HttpServletRequest request) {

    GetAdminInfoDto adminInfo = adminCommandExecutor.login(dto);

    // 로그인 실패
    if (adminInfo == null) {
      throw new IllegalArgumentException("아이디 비밀번호가 일치하지 않습니다.");
    }

    HttpSession session = request.getSession();
    session.setAttribute(SessionConst.SESSION_KEY_ADMIN, adminInfo);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation(value = "회원 로그아웃", notes = "로그아웃 합니다.", tags = "회원 API")
  @PostMapping(value = "/logout")
  public ResponseEntity logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
