package com.classvar.user.application;

import com.classvar.user.application.common.UserMapper;
import com.classvar.user.application.dto.CreateOrUpdateUserDto;
import com.classvar.user.application.dto.LoginDto;
import com.classvar.user.domain.User;
import com.classvar.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCommandExecutor {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public UserCommandExecutor(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public long createUser(CreateOrUpdateUserDto dto) {
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
