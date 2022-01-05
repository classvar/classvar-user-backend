package com.classvar.admin.application.common;

import com.classvar.admin.domain.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
  @Mapping(source = "hashedPW", target = "password")
  Admin toAdmin(String email, String hashedPW, String name, String department, String salt);
}
