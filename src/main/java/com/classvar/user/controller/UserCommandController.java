package com.classvar.user.controller;

import com.classvar.common.SessionConst;
import com.classvar.user.application.UserCommandExecutor;
import com.classvar.user.application.dto.CreateOrUpdateUserDto;
import com.classvar.user.application.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserCommandController {

  private final UserCommandExecutor userCommandExecutor;

  @PostMapping(value = "/signUp")
  public ResponseEntity userSignUp(@Valid @RequestBody CreateOrUpdateUserDto dto) {
    userCommandExecutor.createUser(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/login")
  public ResponseEntity login(@Valid @RequestBody LoginDto dto, HttpServletRequest request) {

    Long loginId = userCommandExecutor.login(dto);

    // login fail
    if (loginId == null) {

    }

    HttpSession session = request.getSession();
    session.setAttribute(SessionConst.LOGIN_ID, loginId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping(value = "/logout")
  public ResponseEntity logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
