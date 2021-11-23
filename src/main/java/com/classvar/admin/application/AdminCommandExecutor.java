package com.classvar.admin.application;

import com.classvar.admin.application.common.AdminMapper;
import com.classvar.admin.application.dto.CreateOrUpdateAdminDto;
import com.classvar.admin.application.dto.LoginDto;
import com.classvar.admin.domain.Admin;
import com.classvar.admin.domain.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminCommandExecutor {

  private final AdminRepository adminRepository;
  private final AdminMapper adminMapper;

  public long signUp(CreateOrUpdateAdminDto dto) {
    Admin admin = adminMapper.toUser(dto);
    adminRepository.save(admin);
    return admin.getId();
  }

  @Transactional
  public Long login(LoginDto dto) {
    Admin findAdmin =
        adminRepository
            .findByEmail(dto.getEmail())
            .filter(a -> a.getPassword().equals(dto.getPassword()))
            .orElse(null);
    if (findAdmin == null) return null;
    return findAdmin.getId();
  }
}
