package com.classvar.admin.application.common;

import com.classvar.admin.application.dto.CreateOrUpdateAdminDto;
import com.classvar.admin.domain.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
  Admin toUser(CreateOrUpdateAdminDto dto);
}
