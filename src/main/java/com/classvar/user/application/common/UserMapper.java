package com.classvar.user.application.common;

import com.classvar.user.application.dto.CreateOrUpdateUserDto;
import com.classvar.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toUser(CreateOrUpdateUserDto dto);
}
