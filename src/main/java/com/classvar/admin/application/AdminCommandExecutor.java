package com.classvar.admin.application;

import com.classvar.admin.application.common.AdminMapper;
import com.classvar.admin.application.common.PasswordEncoder;
import com.classvar.admin.application.dto.request.CreateOrUpdateAdminDto;
import com.classvar.admin.application.dto.request.LoginDto;
import com.classvar.admin.application.dto.response.GetAdminInfoDto;
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
  private final PasswordEncoder passwordEncoder;

  public long signUp(CreateOrUpdateAdminDto dto) {
    String salt = passwordEncoder.getSalt();
    String hashedPW = passwordEncoder.encode(dto.getPassword(), salt);

    Admin toRegister =
        adminMapper.toAdmin(dto.getEmail(), hashedPW, dto.getName(), dto.getDepartment(), salt);
    adminRepository.save(toRegister);

    return toRegister.getId();
  }

  @Transactional
  public GetAdminInfoDto login(LoginDto dto) {
    Admin findAdmin =
        adminRepository
            .findByEmail(dto.getEmail())
            .filter(
                a -> a.getPassword().equals(passwordEncoder.encode(dto.getPassword(), a.getSalt())))
            .orElse(null);
    if (findAdmin == null) return null;
    return adminMapper.toAdminInfoDto(findAdmin);
  }
}
