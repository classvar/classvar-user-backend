package com.classvar.admin.domain;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
  Admin save(Admin admin);

  void delete(Admin admin);

  Optional<Admin> findById(Long adminId);

  Optional<Admin> findByEmail(String email);

  List<Admin> findAllByEmails(List<String> emails);
}
