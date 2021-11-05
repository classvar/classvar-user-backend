package com.classvar.user.application;

import com.classvar.user.application.common.UserMapper;
import com.classvar.user.application.dto.CreateOrUpdateUserDto;
import com.classvar.user.application.dto.LoginDto;
import com.classvar.user.domain.User;
import com.classvar.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandExecutor {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public long signUp(CreateOrUpdateUserDto dto) {
    User user = userMapper.toUser(dto);
    userRepository.save(user);
    return user.getId();
  }

  @Transactional
  public Long login(LoginDto dto) {
    User findUser =
        userRepository
            .findByEmail(dto.getEmail())
            .filter(u -> u.getPassword().equals(dto.getPassword()))
            .orElse(null);
    if (findUser == null) return null;
    return findUser.getId();
  }
}
